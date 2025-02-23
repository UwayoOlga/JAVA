package Question2;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import config.ConfigLoader;

public class OnlineBookstoreSystem extends JFrame {
    // Database connection details
    private static final String DB_URL = ConfigLoader.getProperty("DB_URL");
    private static final String DB_USERNAME = ConfigLoader.getProperty("DB_USERNAME");
    private static final String DB_PASSWORD = ConfigLoader.getProperty("DB_PASSWORD");


    // GUI components
    private JTextField searchField;
    private JButton searchButton;
    private JTable booksTable;
    private DefaultTableModel tableModel;

    public OnlineBookstoreSystem() {
        // Set up the main JFrame with soft pink background
        getContentPane().setBackground(new Color(255, 182, 193));  // Soft pink background
        setTitle("Online Bookstore");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Search panel with flow layout
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setBackground(new Color(255, 182, 193));  // Soft pink background

        // Search field and button
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(255, 105, 180)); // Darker pink for the button
        searchButton.setForeground(Color.WHITE); // White text on button
        searchPanel.add(new JLabel("Search by Title or Author: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Table panel to display books
        tableModel = new DefaultTableModel(new String[] {"BOOK_ID", "TITLE", "AUTHOR", "PRICE", "STOCK"}, 0);
        booksTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(booksTable);

        // Add components to JFrame
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Action listener for search button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBooks(searchField.getText());
            }
        });

        // Populate the table initially
        fetchAllBooks();
    }

    // Establish database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    // Fetch all books from the database and display them
    private void fetchAllBooks() {
        String query = "SELECT * FROM books";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] row = new Object[] {
                        rs.getInt("BOOK_ID"),
                        rs.getString("TITLE"),
                        rs.getString("AUTHOR"),
                        rs.getDouble("PRICE"),
                        rs.getInt("STOCK")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching books: " + e.getMessage());
        }
    }

    // Search for books by TITLE or AUTHOR
    private void searchBooks(String searchTerm) {
        String query = "SELECT * FROM books WHERE TITLE LIKE ? OR AUTHOR LIKE ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");
            ResultSet rs = stmt.executeQuery();

            // Clear the current table data
            tableModel.setRowCount(0);

            while (rs.next()) {
                Object[] row = new Object[] {
                        rs.getInt("BOOK_ID"),
                        rs.getString("TITLE"),
                        rs.getString("AUTHOR"),
                        rs.getDouble("PRICE"),
                        rs.getInt("STOCK")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error searching for books: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OnlineBookstoreSystem().setVisible(true);
            }
        });
    }
}
