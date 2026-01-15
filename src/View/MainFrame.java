/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;
import Model.*;
import Controller.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Ripple Device
 */
public class MainFrame extends javax.swing.JFrame {

    LinkedList<InventoryModel> list = new LinkedList<>();
    static int size =10;
    SimpleStack addNewStack = new SimpleStack(size);

    SimpleStack updateStack = new SimpleStack(size);
    SimpleStack deleteStack = new SimpleStack(size);

    SimpleQueue cartQueue = new SimpleQueue(size);

    SimpleQueue approvalQueue = new SimpleQueue(size);
    SimpleStack approvalHistStack = new SimpleStack(size);

    SimpleStack userCartHistStack = new SimpleStack(size);


    Admin admin = new Admin("admin", "Ishan Maharjan", "ishan@gmail.com","admin123");
    User user = new User("user", "user123", "Javier Don");
    Operations operations = new Operations();

    Structures structures = new Structures();
    Sorting sorting = new Sorting();

    ImageIcon originalIcon = new ImageIcon("images//logo.png");

    ImageIcon icon = new ImageIcon("images//logo2.png");

    // Scale the image (width, height, hints)
    Image scaledImage = originalIcon.getImage().getScaledInstance(
            300, // desired width
            300, // desired height
            Image.SCALE_SMOOTH // scaling algorithm
    );

    // Create new ImageIcon with scaled image
    ImageIcon scaledIcon = new ImageIcon(scaledImage);

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {

        //default init
        initComponents();


        operations.initialData();
        this.list=operations.getList();
        Structures.loadInventoryListToTable(viewTableAdmin, list);
        Structures.loadInventoryListToTable(userViewTbl, list);



    }
    private void adminLogin(){
        try {
            String username = adminUsernameTxt.getText().trim();
            String password = adminPasswordTxt.getText().trim();
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean isAuthenticated = operations.authenticateAdmin(username, password);
            if (isAuthenticated) {
                CardLayout card = (CardLayout) mainPanel.getLayout();
                card.show(mainPanel, "card5");
                CardLayout card2 = (CardLayout) navPanel.getLayout();
                card2.show(navPanel, "card2");
                Structures.loadInventoryListToTable(viewTableAdmin, this.list);
                // Clear login fields
                adminUsernameTxt.setText("");
                adminPasswordTxt.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (NullPointerException e){
            logger.log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "An error occurred during login", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private void userLogin(){
        try {
            String username = userUsernameTxt.getText().trim();
            String password = userPasswordTxt.getText().trim();
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean isAuthenticated = operations.authenticateUser(username, password);
            if (isAuthenticated) {
                CardLayout card = (CardLayout) mainPanel.getLayout();
                card.show(mainPanel, "card8");
                CardLayout card2 = (CardLayout) navPanel.getLayout();
                card2.show(navPanel, "card3");
                Structures.loadInventoryListToTable(userViewTbl, this.list);
                // Clear login fields
                userUsernameTxt.setText("");
                userPasswordTxt.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (NullPointerException e){
            logger.log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "An error occurred during login", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    public void searchAction(String target, JTable table, JComboBox<String> searchCmb){
        try{
            LinkedList <InventoryModel> list = operations.getList();

            if (target.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter a name to search", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (searchCmb.getSelectedItem().equals("Search by ID")){
                sorting.insertionSort(list, "ID");
                int value = Searching.BinarySearchingID(list, target);
                if (value==-1) {
                    JOptionPane.showMessageDialog(null, "ID not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    Structures.loadValuetoTable(table, list, value);
                }
            }
            else if (searchCmb.getSelectedItem().equals("Search by Name")){
                sorting.insertionSort(list, "Name");
                int value = Searching.BinarySearchingName(list, target);
                if (value==-1) {
                    JOptionPane.showMessageDialog(null, "Name not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    Structures.loadValuetoTable(table, list, value);
                }
            }
            else if (searchCmb.getSelectedItem().equals("Search by Company")){
                sorting.insertionSort(list, "Company");
                int value = Searching.BinarySearchingCompany(list, target);
                if (value==-1) {
                    JOptionPane.showMessageDialog(null, "Company not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    List<Integer> results = Searching.BinarySearchingCompanyName(list, target);
                    structures.loadlistToTable(table, list, results);
                }
            }
        }catch (NullPointerException e){
            logger.log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "An error occurred during searching", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    public void sortAction(JTable table, JComboBox<String> sortCmb, boolean ascending){
        LinkedList <InventoryModel> list = operations.getList();
        try {
            if (sortCmb.getSelectedItem().equals("Sort by ID")) {
                sorting.insertionSort(list, "ID");
            } else if (sortCmb.getSelectedItem().equals("Sort by Name")) {
                sorting.insertionSort(list, "Name");
            } else if (sortCmb.getSelectedItem().equals("Sort by Price")) {
                sorting.insertionSort(list, "Price");
            } else if (sortCmb.getSelectedItem().equals("Sort by Quantity")) {
                sorting.insertionSort(list, "Quantity");
            } else if (sortCmb.getSelectedItem().equals("Sort by Company")) {
                sorting.insertionSort(list, "Company");
            } else if (sortCmb.getSelectedItem().equals("Sort by Date")) {
                sorting.insertionSort(list, "Date");
            }

            if (!ascending) {
                LinkedList<InventoryModel> reversedList = new LinkedList<>();
                for (int i = list.size() - 1; i >= 0; i--) {
                    reversedList.add(list.get(i));
                }
                list = reversedList;
            }

            Structures.loadInventoryListToTable(table, list);
        }
        catch (NullPointerException e){
            logger.log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "An error occurred during sorting", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navPanel = new javax.swing.JPanel();
        selectPanel = new javax.swing.JPanel();
        selectAdmin = new javax.swing.JButton();
        selectUser = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel(scaledIcon);
        adminPanel = new javax.swing.JPanel();
        adminNavPanel = new javax.swing.JPanel();
        viewAllBtn = new javax.swing.JButton();
        addNewPanelBtn = new javax.swing.JButton();
        updatePanelBtn = new javax.swing.JButton();
        deletePanelBtn = new javax.swing.JButton();
        approvePanelBtn = new javax.swing.JButton();
        logo = new javax.swing.JLabel(scaledIcon);
        adminLogoutBtn = new javax.swing.JButton();
        userPanel = new javax.swing.JPanel();
        userNavPanel = new javax.swing.JPanel();
        userViewBtn = new javax.swing.JButton();
        useCartBtn = new javax.swing.JButton();
        userLogoutBtn = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel(scaledIcon);
        mainPanel = new javax.swing.JPanel();
        adminLoginPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        adminLoginBtn = new javax.swing.JButton();
        adminUsernameTxt = new javax.swing.JTextField();
        adminPasswordTxt = new javax.swing.JPasswordField();
        userLoginPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        userUsernameTxt = new javax.swing.JTextField();
        userLoginBtn = new javax.swing.JButton();
        userPasswordTxt = new javax.swing.JPasswordField();
        adminActionsPanel = new javax.swing.JPanel();
        View = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewTableAdmin = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        adminSearchTxt = new javax.swing.JTextField();
        searchAdmin = new javax.swing.JButton();
        adminSortCmb = new javax.swing.JComboBox<>();
        adminSearchCmb = new javax.swing.JComboBox<>();
        adminAscSortBtn = new javax.swing.JButton();
        clearAdminBtn = new javax.swing.JButton();
        adminDescSortBtn = new javax.swing.JButton();
        addNewPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        addNewBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        addNewTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        addIDText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        addNameText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        addCpNameText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        addPriceText = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        addQntText = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        addDateText = new javax.swing.JTextField();
        addNewClearBtn = new javax.swing.JButton();
        updatePanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        updateBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        updateTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        upIDlabel = new javax.swing.JLabel();
        upIDText = new javax.swing.JTextField();
        upNameLabel = new javax.swing.JLabel();
        upCpNameText = new javax.swing.JTextField();
        upCpNameLabel = new javax.swing.JLabel();
        upNameText = new javax.swing.JTextField();
        upPriceLabel = new javax.swing.JLabel();
        upPriceText = new javax.swing.JTextField();
        upQntLabel = new javax.swing.JLabel();
        upQntText = new javax.swing.JTextField();
        upDateLabel = new javax.swing.JLabel();
        upDateText = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        updateClearBtn = new javax.swing.JButton();
        deletePanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        deleteText = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        deleteTable = new javax.swing.JTable();
        deleteLabel = new javax.swing.JLabel();
        deleteBtn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        deleteViewTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        clearDeleteHistoryBtn = new javax.swing.JButton();
        approvePanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        approvalTable = new javax.swing.JTable();
        approveBtn = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        approvalHistoryTable = new javax.swing.JTable();
        adminApprovalClearAllBtn = new javax.swing.JButton();
        adminApprovalClearBtn = new javax.swing.JButton();
        approveAllBtn = new javax.swing.JButton();
        userActionsPanel = new javax.swing.JPanel();
        userViewPanel = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        userViewTbl = new javax.swing.JTable();
        cartIDTxt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cartBtn = new javax.swing.JButton();
        grandTotalView = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        clearUserBtn = new javax.swing.JButton();
        userSearchCmb1 = new javax.swing.JComboBox<>();
        userSearchBtn = new javax.swing.JButton();
        userSearchTxt1 = new javax.swing.JTextField();
        userSortCmb1 = new javax.swing.JComboBox<>();
        userAscSortBtn1 = new javax.swing.JButton();
        userDescSortBtn = new javax.swing.JButton();
        userCartPanel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        userCartTbl = new javax.swing.JTable();
        yserCheckoutBtn = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        userCartHistoryTbl = new javax.swing.JTable();
        userClearCheckoutBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dental Inventory Solutions");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(icon.getImage());
        setMinimumSize(new java.awt.Dimension(1100, 900));
        setResizable(false);
        setSize(new java.awt.Dimension(1100, 900));

        navPanel.setBackground(new java.awt.Color(3, 57, 108));
        navPanel.setLayout(new java.awt.CardLayout());

        selectPanel.setBackground(new java.awt.Color(3, 57, 108));

        selectAdmin.setText("ADIMIN");
        selectAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAdminActionPerformed(evt);
            }
        });

        selectUser.setText("USER");
        selectUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectUserActionPerformed(evt);
            }
        });

        jLabel22.setText("jLabel22");

        javax.swing.GroupLayout selectPanelLayout = new javax.swing.GroupLayout(selectPanel);
        selectPanel.setLayout(selectPanelLayout);
        selectPanelLayout.setHorizontalGroup(
            selectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectPanelLayout.createSequentialGroup()
                .addComponent(jLabel22)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(selectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(selectAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(selectUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        selectPanelLayout.setVerticalGroup(
            selectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectPanelLayout.createSequentialGroup()
                .addComponent(jLabel22)
                .addGap(16, 16, 16)
                .addComponent(selectAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(selectUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(568, Short.MAX_VALUE))
        );

        jLabel22.setHorizontalAlignment(SwingConstants.CENTER);

        navPanel.add(selectPanel, "card4");

        adminPanel.setBackground(new java.awt.Color(3, 57, 108));
        adminPanel.setForeground(new java.awt.Color(3, 57, 108));
        adminPanel.setAlignmentX(0.0F);
        adminPanel.setAlignmentY(0.0F);
        adminPanel.setMaximumSize(new java.awt.Dimension(300, 32767));
        adminPanel.setPreferredSize(new java.awt.Dimension(300, 900));

        adminNavPanel.setLayout(new java.awt.GridLayout(5, 1));

        viewAllBtn.setText("View All Products");
        viewAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAllBtnActionPerformed(evt);
            }
        });
        adminNavPanel.add(viewAllBtn);

        addNewPanelBtn.setText("Add new Product");
        addNewPanelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewPanelBtnActionPerformed(evt);
            }
        });
        adminNavPanel.add(addNewPanelBtn);

        updatePanelBtn.setText("Update Product");
        updatePanelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePanelBtnActionPerformed(evt);
            }
        });
        adminNavPanel.add(updatePanelBtn);

        deletePanelBtn.setText("Delete Product");
        deletePanelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePanelBtnActionPerformed(evt);
            }
        });
        adminNavPanel.add(deletePanelBtn);

        approvePanelBtn.setText("Approve Checkouts");
        approvePanelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approvePanelBtnActionPerformed(evt);
            }
        });
        adminNavPanel.add(approvePanelBtn);

        logo.setText("logo");

        adminLogoutBtn.setText("Log Out");
        adminLogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminLogoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminPanelLayout = new javax.swing.GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanelLayout);
        adminPanelLayout.setHorizontalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminPanelLayout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addComponent(adminLogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addComponent(logo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adminNavPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        adminPanelLayout.setVerticalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addComponent(logo)
                .addGap(10, 10, 10)
                .addComponent(adminNavPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 489, Short.MAX_VALUE)
                .addComponent(adminLogoutBtn)
                .addGap(111, 111, 111))
        );

        logo.setHorizontalAlignment(SwingConstants.CENTER);

        navPanel.add(adminPanel, "card2");

        userPanel.setBackground(new java.awt.Color(3, 57, 108));
        userPanel.setPreferredSize(new java.awt.Dimension(300, 900));

        userNavPanel.setLayout(new java.awt.GridLayout(2, 1));

        userViewBtn.setText("View");
        userViewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userViewBtnActionPerformed(evt);
            }
        });
        userNavPanel.add(userViewBtn);

        useCartBtn.setText("Cart");
        useCartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useCartBtnActionPerformed(evt);
            }
        });
        userNavPanel.add(useCartBtn);

        userLogoutBtn.setText("Log Out");
        userLogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userLogoutBtnActionPerformed(evt);
            }
        });

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Name");

        jLabel23.setText("jLabel23");

        javax.swing.GroupLayout userPanelLayout = new javax.swing.GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userPanelLayout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addComponent(userLogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(userPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(userPanelLayout.createSequentialGroup()
                .addComponent(jLabel23)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(userPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userNavPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        userPanelLayout.setVerticalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanelLayout.createSequentialGroup()
                .addComponent(jLabel23)
                .addGap(10, 10, 10)
                .addComponent(userNavPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 426, Short.MAX_VALUE)
                .addComponent(userLogoutBtn)
                .addGap(100, 100, 100))
        );

        jLabel23.setHorizontalAlignment(SwingConstants.CENTER);

        navPanel.add(userPanel, "card3");

        mainPanel.setBackground(new java.awt.Color(179, 205, 224));
        mainPanel.setPreferredSize(new java.awt.Dimension(800, 900));
        mainPanel.setLayout(new java.awt.CardLayout());

        adminLoginPanel.setBackground(new java.awt.Color(255, 255, 255));
        adminLoginPanel.setPreferredSize(new java.awt.Dimension(800, 900));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel17.setText("Admin Login");

        adminLoginBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        adminLoginBtn.setText("LOGIN");
        adminLoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminLoginBtnActionPerformed(evt);
            }
        });

        adminUsernameTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        adminUsernameTxt.setText("admin");
        adminUsernameTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        adminUsernameTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adminUsernameTxtFocusGained(evt);
            }
        });

        adminPasswordTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        adminPasswordTxt.setText("12345678");
        adminPasswordTxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Password"));
        adminPasswordTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adminPasswordTxtFocusGained(evt);
            }
        });
        adminPasswordTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                adminPasswordTxtKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout adminLoginPanelLayout = new javax.swing.GroupLayout(adminLoginPanel);
        adminLoginPanel.setLayout(adminLoginPanelLayout);
        adminLoginPanelLayout.setHorizontalGroup(
            adminLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLoginPanelLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addGroup(adminLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminUsernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminLoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(250, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminLoginPanelLayout.createSequentialGroup()
                .addContainerGap(299, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(299, 299, 299))
        );
        adminLoginPanelLayout.setVerticalGroup(
            adminLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLoginPanelLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jLabel17)
                .addGap(40, 40, 40)
                .addComponent(adminUsernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(adminPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(adminLoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        mainPanel.add(adminLoginPanel, "card7");

        userLoginPanel.setBackground(new java.awt.Color(255, 255, 255));
        userLoginPanel.setPreferredSize(new java.awt.Dimension(800, 900));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel13.setText("User Login");

        userUsernameTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userUsernameTxt.setText("user");
        userUsernameTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        userUsernameTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userUsernameTxtFocusGained(evt);
            }
        });

        userLoginBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        userLoginBtn.setText("LOGIN");
        userLoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userLoginBtnActionPerformed(evt);
            }
        });

        userPasswordTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userPasswordTxt.setText("jPasswordField1");
        userPasswordTxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Password"));
        userPasswordTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userPasswordTxtFocusGained(evt);
            }
        });
        userPasswordTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userPasswordTxtKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout userLoginPanelLayout = new javax.swing.GroupLayout(userLoginPanel);
        userLoginPanel.setLayout(userLoginPanelLayout);
        userLoginPanelLayout.setHorizontalGroup(
            userLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userLoginPanelLayout.createSequentialGroup()
                .addGroup(userLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userLoginPanelLayout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(jLabel13))
                    .addGroup(userLoginPanelLayout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addGroup(userLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userUsernameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(userPasswordTxt)
                            .addComponent(userLoginBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(250, Short.MAX_VALUE))
        );
        userLoginPanelLayout.setVerticalGroup(
            userLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userLoginPanelLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jLabel13)
                .addGap(40, 40, 40)
                .addComponent(userUsernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(userPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(userLoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        mainPanel.add(userLoginPanel, "card6");

        adminActionsPanel.setPreferredSize(new java.awt.Dimension(800, 900));
        adminActionsPanel.setLayout(new java.awt.CardLayout());

        View.setBackground(new java.awt.Color(179, 205, 224));
        View.setPreferredSize(new java.awt.Dimension(800, 900));

        viewTableAdmin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        viewTableAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Company", "Price", "Quantity", "Added Date"
            }
        ));
        jScrollPane1.setViewportView(viewTableAdmin);
        if (viewTableAdmin.getColumnModel().getColumnCount() > 0) {
            viewTableAdmin.getColumnModel().getColumn(0).setResizable(false);
            viewTableAdmin.getColumnModel().getColumn(0).setPreferredWidth(10);
            viewTableAdmin.getColumnModel().getColumn(3).setResizable(false);
            viewTableAdmin.getColumnModel().getColumn(3).setPreferredWidth(15);
            viewTableAdmin.getColumnModel().getColumn(4).setResizable(false);
            viewTableAdmin.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 50)); // NOI18N
        jLabel11.setText("Dental Inventory Solutions");

        adminSearchTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        adminSearchTxt.setText("Search");
        adminSearchTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adminSearchTxtFocusGained(evt);
            }
        });

        searchAdmin.setText("Search");
        searchAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAdminActionPerformed(evt);
            }
        });

        adminSortCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sort by ID", "Sort by Name", "Sort by Company", "Sort by Price", "Sort by Quantity", "Sort by Added Date" }));

        adminSearchCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search by ID", "Search by Name", "Search by Company" }));

        adminAscSortBtn.setText("Ascending");
        adminAscSortBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminAscSortBtnActionPerformed(evt);
            }
        });

        clearAdminBtn.setText("Clear");
        clearAdminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAdminBtnActionPerformed(evt);
            }
        });

        adminDescSortBtn.setText("Descending ");
        adminDescSortBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminDescSortBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ViewLayout = new javax.swing.GroupLayout(View);
        View.setLayout(ViewLayout);
        ViewLayout.setHorizontalGroup(
            ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                    .addGroup(ViewLayout.createSequentialGroup()
                        .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ViewLayout.createSequentialGroup()
                                .addComponent(adminSearchCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clearAdminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(adminSearchTxt))
                        .addGap(156, 156, 156)
                        .addComponent(adminSortCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(adminAscSortBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adminDescSortBtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(ViewLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel11)
                .addGap(0, 110, Short.MAX_VALUE))
        );
        ViewLayout.setVerticalGroup(
            ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel11)
                .addGap(20, 20, 20)
                .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminSearchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(adminSortCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(adminAscSortBtn)
                        .addComponent(adminDescSortBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminSearchCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchAdmin)
                        .addComponent(clearAdminBtn)))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        adminActionsPanel.add(View, "card2");

        addNewPanel.setBackground(new java.awt.Color(179, 205, 224));
        addNewPanel.setPreferredSize(new java.awt.Dimension(800, 900));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 50)); // NOI18N
        jLabel12.setText("Dental Inventory Solutions");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Add New Product");

        addNewBtn.setText("Add");
        addNewBtn.setPreferredSize(new java.awt.Dimension(90, 25));
        addNewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewBtnActionPerformed(evt);
            }
        });

        addNewTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addNewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Company", "Price", "Quantity", "Date"
            }
        ));
        jScrollPane2.setViewportView(addNewTable);
        if (addNewTable.getColumnModel().getColumnCount() > 0) {
            addNewTable.getColumnModel().getColumn(0).setResizable(false);
            addNewTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            addNewTable.getColumnModel().getColumn(3).setResizable(false);
            addNewTable.getColumnModel().getColumn(3).setPreferredWidth(15);
            addNewTable.getColumnModel().getColumn(4).setResizable(false);
            addNewTable.getColumnModel().getColumn(4).setPreferredWidth(15);
        }

        jPanel2.setBackground(new java.awt.Color(179, 205, 224));
        jPanel2.setLayout(new java.awt.GridLayout(6, 3, 10, 15));

        jLabel2.setText("ID");
        jPanel2.add(jLabel2);
        jPanel2.add(addIDText);

        jLabel3.setText("Name");
        jPanel2.add(jLabel3);
        jPanel2.add(addNameText);

        jLabel4.setText("Company Name");
        jPanel2.add(jLabel4);
        jPanel2.add(addCpNameText);

        jLabel6.setText("Price");
        jPanel2.add(jLabel6);
        jPanel2.add(addPriceText);

        jLabel7.setText("Quantity");
        jPanel2.add(jLabel7);
        jPanel2.add(addQntText);

        jLabel8.setText("Date");
        jPanel2.add(jLabel8);
        jPanel2.add(addDateText);

        addNewClearBtn.setText("Clear");
        addNewClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewClearBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addNewPanelLayout = new javax.swing.GroupLayout(addNewPanel);
        addNewPanel.setLayout(addNewPanelLayout);
        addNewPanelLayout.setHorizontalGroup(
            addNewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addNewPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(300, 300, 300))
            .addGroup(addNewPanelLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addNewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addNewClearBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addNewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        addNewPanelLayout.setVerticalGroup(
            addNewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addNewPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel12)
                .addGap(120, 120, 120)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(addNewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addNewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addNewClearBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
        );

        adminActionsPanel.add(addNewPanel, "card3");

        updatePanel.setBackground(new java.awt.Color(179, 205, 224));
        updatePanel.setPreferredSize(new java.awt.Dimension(800, 900));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Update Product");
        jLabel9.setRequestFocusEnabled(false);

        updateBtn.setText("Update");
        updateBtn.setPreferredSize(new java.awt.Dimension(90, 25));
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        updateTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        updateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Company", "Price", "Quantity", "Date"
            }
        ));
        jScrollPane3.setViewportView(updateTable);
        if (updateTable.getColumnModel().getColumnCount() > 0) {
            updateTable.getColumnModel().getColumn(0).setResizable(false);
            updateTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            updateTable.getColumnModel().getColumn(3).setResizable(false);
            updateTable.getColumnModel().getColumn(3).setPreferredWidth(15);
            updateTable.getColumnModel().getColumn(4).setResizable(false);
            updateTable.getColumnModel().getColumn(4).setPreferredWidth(15);
        }

        jPanel1.setBackground(new java.awt.Color(179, 205, 224));
        jPanel1.setLayout(new java.awt.GridLayout(6, 2, 10, 15));

        upIDlabel.setText("ID");
        jPanel1.add(upIDlabel);

        upIDText.setPreferredSize(new java.awt.Dimension(150, 22));
        upIDText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                upIDTextKeyPressed(evt);
            }
        });
        jPanel1.add(upIDText);

        upNameLabel.setText("Name");
        jPanel1.add(upNameLabel);

        upCpNameText.setPreferredSize(new java.awt.Dimension(150, 22));
        jPanel1.add(upCpNameText);

        upCpNameLabel.setText("Company");
        jPanel1.add(upCpNameLabel);

        upNameText.setColumns(2);
        jPanel1.add(upNameText);

        upPriceLabel.setText("Price");
        jPanel1.add(upPriceLabel);

        upPriceText.setPreferredSize(new java.awt.Dimension(150, 22));
        jPanel1.add(upPriceText);

        upQntLabel.setText("Quantity");
        jPanel1.add(upQntLabel);

        upQntText.setPreferredSize(new java.awt.Dimension(150, 22));
        jPanel1.add(upQntText);

        upDateLabel.setText("Date");
        jPanel1.add(upDateLabel);

        upDateText.setPreferredSize(new java.awt.Dimension(150, 22));
        jPanel1.add(upDateText);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 50)); // NOI18N
        jLabel15.setText("Dental Inventory Solutions");

        updateClearBtn.setText("Clear");
        updateClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateClearBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updatePanelLayout = new javax.swing.GroupLayout(updatePanel);
        updatePanel.setLayout(updatePanelLayout);
        updatePanelLayout.setHorizontalGroup(
            updatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(updateClearBtn)
                .addGap(102, 102, 102)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
            .addComponent(jScrollPane3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updatePanelLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(updatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updatePanelLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(110, 110, 110))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updatePanelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(304, 304, 304))))
        );
        updatePanelLayout.setVerticalGroup(
            updatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatePanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel15)
                .addGap(120, 120, 120)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(updatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateClearBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
        );

        adminActionsPanel.add(updatePanel, "card4");

        deletePanel.setBackground(new java.awt.Color(179, 205, 224));
        deletePanel.setPreferredSize(new java.awt.Dimension(800, 900));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 50)); // NOI18N
        jLabel14.setText("Dental Inventory Solutions");

        deleteTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deleteTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "Company"
            }
        ));
        jScrollPane4.setViewportView(deleteTable);
        if (deleteTable.getColumnModel().getColumnCount() > 0) {
            deleteTable.getColumnModel().getColumn(0).setResizable(false);
            deleteTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        deleteLabel.setText("Enter ID");

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        deleteViewTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deleteViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "Company"
            }
        ));
        jScrollPane5.setViewportView(deleteViewTable);
        if (deleteViewTable.getColumnModel().getColumnCount() > 0) {
            deleteViewTable.getColumnModel().getColumn(0).setResizable(false);
            deleteViewTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        jLabel1.setText("Delete History");

        jLabel10.setText("List of all products");

        clearDeleteHistoryBtn.setText("Clear");
        clearDeleteHistoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearDeleteHistoryBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deletePanelLayout = new javax.swing.GroupLayout(deletePanel);
        deletePanel.setLayout(deletePanelLayout);
        deletePanelLayout.setHorizontalGroup(
            deletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deletePanelLayout.createSequentialGroup()
                .addGroup(deletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deletePanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(deleteLabel)
                        .addGap(41, 41, 41)
                        .addComponent(deleteText, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(deleteBtn))
                    .addGroup(deletePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(deletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(deletePanelLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(deletePanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clearDeleteHistoryBtn))))
                    .addGroup(deletePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(deletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(deletePanelLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        deletePanelLayout.setVerticalGroup(
            deletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deletePanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel14)
                .addGap(47, 47, 47)
                .addGroup(deletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteLabel)
                    .addComponent(deleteText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(deletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(clearDeleteHistoryBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );

        adminActionsPanel.add(deletePanel, "card5");

        approvePanel.setBackground(new java.awt.Color(179, 205, 224));

        approvalTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        approvalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Company Name", "Price", "Quantity", "Date"
            }
        ));
        jScrollPane6.setViewportView(approvalTable);
        if (approvalTable.getColumnModel().getColumnCount() > 0) {
            approvalTable.getColumnModel().getColumn(0).setResizable(false);
            approvalTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            approvalTable.getColumnModel().getColumn(3).setResizable(false);
            approvalTable.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        approveBtn.setText("Approve");
        approveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveBtnActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 50)); // NOI18N
        jLabel24.setText("Dental Inventory Solutions");

        approvalHistoryTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        approvalHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Company Name", "Price", "Quantity", "Date"
            }
        ));
        jScrollPane10.setViewportView(approvalHistoryTable);
        if (approvalHistoryTable.getColumnModel().getColumnCount() > 0) {
            approvalHistoryTable.getColumnModel().getColumn(0).setResizable(false);
            approvalHistoryTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            approvalHistoryTable.getColumnModel().getColumn(3).setResizable(false);
            approvalHistoryTable.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        adminApprovalClearAllBtn.setText("Clear History");
        adminApprovalClearAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminApprovalClearAllBtnActionPerformed(evt);
            }
        });

        adminApprovalClearBtn.setText("Clear");
        adminApprovalClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminApprovalClearBtnActionPerformed(evt);
            }
        });

        approveAllBtn.setText("Approve All");
        approveAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveAllBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout approvePanelLayout = new javax.swing.GroupLayout(approvePanel);
        approvePanel.setLayout(approvePanelLayout);
        approvePanelLayout.setHorizontalGroup(
            approvePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
            .addComponent(jScrollPane10)
            .addGroup(approvePanelLayout.createSequentialGroup()
                .addGroup(approvePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, approvePanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(adminApprovalClearBtn)
                        .addGap(51, 51, 51)
                        .addComponent(adminApprovalClearAllBtn))
                    .addGroup(approvePanelLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel24)
                        .addGap(0, 104, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(approvePanelLayout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(approveBtn)
                .addGap(33, 33, 33)
                .addComponent(approveAllBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        approvePanelLayout.setVerticalGroup(
            approvePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, approvePanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel24)
                .addGap(38, 38, 38)
                .addGroup(approvePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(approveBtn)
                    .addComponent(approveAllBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(approvePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adminApprovalClearBtn)
                    .addComponent(adminApprovalClearAllBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        adminActionsPanel.add(approvePanel, "card6");

        mainPanel.add(adminActionsPanel, "card5");

        userActionsPanel.setBackground(new java.awt.Color(179, 205, 224));
        userActionsPanel.setPreferredSize(new java.awt.Dimension(800, 900));
        userActionsPanel.setLayout(new java.awt.CardLayout());

        userViewPanel.setBackground(new java.awt.Color(179, 205, 224));
        userViewPanel.setPreferredSize(new java.awt.Dimension(803, 900));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 50)); // NOI18N
        jLabel18.setText("Dental Inventory Solutions");

        userViewTbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userViewTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Company", "Price", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(userViewTbl);
        if (userViewTbl.getColumnModel().getColumnCount() > 0) {
            userViewTbl.getColumnModel().getColumn(0).setResizable(false);
            userViewTbl.getColumnModel().getColumn(0).setPreferredWidth(10);
            userViewTbl.getColumnModel().getColumn(1).setResizable(false);
            userViewTbl.getColumnModel().getColumn(3).setResizable(false);
        }

        cartIDTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cartIDTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cartIDTxtKeyPressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Enter ID");

        cartBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cartBtn.setText("Add to Cart");
        cartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartBtnActionPerformed(evt);
            }
        });

        grandTotalView.setBackground(new java.awt.Color(255, 255, 255));
        grandTotalView.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        grandTotalView.setText("Grand Total =");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Quantity");

        clearUserBtn.setText("Clear");
        clearUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearUserBtnActionPerformed(evt);
            }
        });

        userSearchCmb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search by ID", "Search by Name", "Search by Company" }));

        userSearchBtn.setText("Search");
        userSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userSearchBtnActionPerformed(evt);
            }
        });

        userSearchTxt1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userSearchTxt1.setText("Search");
        userSearchTxt1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userSearchTxt1FocusGained(evt);
            }
        });

        userSortCmb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sort by ID", "Sort by Name", "Sort by Company", "Sort by Price", "Sort by Quantity", "Sort by Added Date" }));

        userAscSortBtn1.setText("Ascending");
        userAscSortBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userAscSortBtn1ActionPerformed(evt);
            }
        });

        userDescSortBtn.setText("Descending ");
        userDescSortBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userDescSortBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userViewPanelLayout = new javax.swing.GroupLayout(userViewPanel);
        userViewPanel.setLayout(userViewPanelLayout);
        userViewPanelLayout.setHorizontalGroup(
            userViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userViewPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(110, 110, 110))
            .addGroup(userViewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addGroup(userViewPanelLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(30, 30, 30)
                        .addComponent(cartIDTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel20)
                        .addGap(30, 30, 30)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(cartBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(grandTotalView, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(userViewPanelLayout.createSequentialGroup()
                        .addGroup(userViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(userViewPanelLayout.createSequentialGroup()
                                .addComponent(userSearchCmb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userSearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clearUserBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(userSearchTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(156, 156, 156)
                        .addComponent(userSortCmb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userAscSortBtn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userDescSortBtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        userViewPanelLayout.setVerticalGroup(
            userViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userViewPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(userViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(cartIDTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel20)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grandTotalView, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(userViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userSearchTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(userViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(userSortCmb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(userAscSortBtn1)
                        .addComponent(userDescSortBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(userViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userSearchCmb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(userViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(userSearchBtn)
                        .addComponent(clearUserBtn)))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        userActionsPanel.add(userViewPanel, "card2");

        userCartPanel.setBackground(new java.awt.Color(179, 205, 224));
        userCartPanel.setPreferredSize(new java.awt.Dimension(803, 900));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 50)); // NOI18N
        jLabel19.setText("Dental Inventory Solutions");

        userCartTbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userCartTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Company", "Price", "Quantity", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(userCartTbl);
        if (userCartTbl.getColumnModel().getColumnCount() > 0) {
            userCartTbl.getColumnModel().getColumn(0).setResizable(false);
            userCartTbl.getColumnModel().getColumn(0).setPreferredWidth(10);
            userCartTbl.getColumnModel().getColumn(1).setResizable(false);
            userCartTbl.getColumnModel().getColumn(3).setResizable(false);
            userCartTbl.getColumnModel().getColumn(5).setResizable(false);
            userCartTbl.getColumnModel().getColumn(5).setHeaderValue("Status");
        }

        yserCheckoutBtn.setText("Checkout");
        yserCheckoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yserCheckoutBtnActionPerformed(evt);
            }
        });

        userCartHistoryTbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userCartHistoryTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Company", "Price", "Quantity", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(userCartHistoryTbl);
        if (userCartHistoryTbl.getColumnModel().getColumnCount() > 0) {
            userCartHistoryTbl.getColumnModel().getColumn(0).setResizable(false);
            userCartHistoryTbl.getColumnModel().getColumn(0).setPreferredWidth(10);
            userCartHistoryTbl.getColumnModel().getColumn(1).setResizable(false);
            userCartHistoryTbl.getColumnModel().getColumn(3).setResizable(false);
            userCartHistoryTbl.getColumnModel().getColumn(5).setResizable(false);
            userCartHistoryTbl.getColumnModel().getColumn(5).setHeaderValue("Status");
        }

        userClearCheckoutBtn.setText("Clear");
        userClearCheckoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userClearCheckoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userCartPanelLayout = new javax.swing.GroupLayout(userCartPanel);
        userCartPanel.setLayout(userCartPanelLayout);
        userCartPanelLayout.setHorizontalGroup(
            userCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userCartPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(110, 110, 110))
            .addGroup(userCartPanelLayout.createSequentialGroup()
                .addGap(702, 702, 702)
                .addGroup(userCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(userClearCheckoutBtn)
                    .addComponent(yserCheckoutBtn))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userCartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userCartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9)
                .addContainerGap())
        );
        userCartPanelLayout.setVerticalGroup(
            userCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userCartPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel19)
                .addGap(55, 55, 55)
                .addComponent(yserCheckoutBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(userClearCheckoutBtn)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        userActionsPanel.add(userCartPanel, "card3");

        mainPanel.add(userActionsPanel, "card8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents



    private void addNewPanelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewPanelBtnActionPerformed
        // TODO add your handling code here:
        //Switch to Add new Panel
        CardLayout card = (CardLayout)adminActionsPanel.getLayout();
        card.show(adminActionsPanel, "card3");


    }//GEN-LAST:event_addNewPanelBtnActionPerformed

    private void addNewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewBtnActionPerformed
        // TODO add your handling code here:
        //Btn to add new product to list

        String id = addIDText.getText().trim();
        String name = addNameText.getText().trim();
        String company = addCpNameText.getText().trim();
        String price = addPriceText.getText().trim();
        String quantity = addQntText.getText().trim();
        String date = addDateText.getText().trim();
        boolean success;

        try{
             success = operations.addProduct(addNewPanel,id, name, company, price, quantity, date);
        }
        catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid data format","Error", JOptionPane.ERROR_MESSAGE);
                return;
        }
        catch(NullPointerException e){
            logger.log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "An error occurred while deleting the product", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (success){
            this.list = operations.getList();
            Structures.loadInventoryListToTable(viewTableAdmin, this.list);

            JOptionPane.showMessageDialog(this, "Product added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            //push to add history stack
            addNewStack.push(id, name, company, Double.parseDouble(price),Integer.parseInt(quantity), date);

            int rowCount = addNewTable.getRowCount();
            if(rowCount==size-1||addNewStack.isFull()){
                JOptionPane.showMessageDialog(this, "Add new product history is full, clear list to add more", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Structures.loadFromStack(addNewTable,addNewStack.getStackData(),addNewStack.getTop());


            //Clear the text fields after adding
            addIDText.setText("");
            addNameText.setText("");
            addCpNameText.setText("");
            addPriceText.setText("");
            addQntText.setText("");
            addDateText.setText("");
        }

    }//GEN-LAST:event_addNewBtnActionPerformed

    private void updatePanelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePanelBtnActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)adminActionsPanel.getLayout();
        card.show(adminActionsPanel, "card4");
        Structures.loadFromStack(updateTable, updateStack.getStackData(),updateStack.getTop());
    }//GEN-LAST:event_updatePanelBtnActionPerformed

    private void viewAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAllBtnActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)adminActionsPanel.getLayout();
        card.show(adminActionsPanel, "card2");
        Structures.loadInventoryListToTable(viewTableAdmin, this.list);
    }//GEN-LAST:event_viewAllBtnActionPerformed

    private void deletePanelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePanelBtnActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)adminActionsPanel.getLayout();
        card.show(adminActionsPanel, "card5");
        Structures.loadFromStack(deleteTable, deleteStack.getStackData(),deleteStack.getTop());
        Structures.loadInventoryListToTable(deleteViewTable, list);

    }//GEN-LAST:event_deletePanelBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        String productId=deleteText.getText();
        InventoryModel l =null;
        try {
            if (productId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Product ID To Update", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            LinkedList<InventoryModel> checkList = operations.getList();

            for (InventoryModel li : checkList) {
                if (li.getProductID().equals(productId)) {
                    l = li;
                }
            }
            if (l == null) {
                JOptionPane.showMessageDialog(this, "Product ID not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int rowCount = deleteTable.getRowCount();
            if(rowCount==size-1||deleteStack.isFull()){
                JOptionPane.showMessageDialog(this, "Delete history is full, remove product from history to continue", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            InventoryModel model = new InventoryModel(l.getProductID(), l.getProductName(), l.getProductCompany(), l.getProductPrice(), l.getProductQuantity(), l.getAddedDate());
            operations.deleteProduct(l,deletePanel);

            //push to delete history stack
            deleteStack.push(model.getProductID(), model.getProductName(), model.getProductCompany(), model.getProductPrice(), model.getProductQuantity(), model.getAddedDate());

            this.list=operations.getList();
            Structures.loadFromStack(deleteTable,deleteStack.getStackData(),deleteStack.getTop());
            Structures.loadInventoryListToTable(deleteViewTable, this.list);
            //Logic to delete product from the list

        }catch (NullPointerException e){
            logger.log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Prodcut ID not found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        deleteText.setText("");


    }//GEN-LAST:event_deleteBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        String id = upIDText.getText().trim();
        String name = upNameText.getText().trim();
        String company = upCpNameText.getText().trim();
        String price = upPriceText.getText().trim();
        String quantity = upQntText.getText().trim();
        String date = upDateText.getText().trim();
        boolean success;

        try{
            int rowCount = updateTable.getRowCount();
            if(rowCount==size-1){
                JOptionPane.showMessageDialog(this, "Update history is full, clear list to add more", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            success=operations.updateProduct(updatePanel,id, name, company, price, quantity, date);

            if (success){
                this.list=operations.getList();
                Structures.loadInventoryListToTable(viewTableAdmin, this.list);
                JOptionPane.showMessageDialog(this, "Product updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                //push to add history stack
                updateStack.push(id, name, company, Double.parseDouble(price), Integer.parseInt(quantity), date);

                Structures.loadFromStack(updateTable,updateStack.getStackData(),updateStack.getTop());

                //Clear the text fields after updating
                upIDText.setText("");
                upNameText.setText("");
                upCpNameText.setText("");
                upPriceText.setText("");
                upQntText.setText("");
                upDateText.setText("");
            }

        }
        catch (NullPointerException e){
            logger.log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (NumberFormatException e){
            logger.log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Price and Quantity", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_updateBtnActionPerformed

    private void upIDTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_upIDTextKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                if (upIDText.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Enter Product ID To Update", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                LinkedList<InventoryModel> checkList = operations.getList();
                InventoryModel l = null;
                for (InventoryModel li : checkList) {
                    if (li.getProductID().equals(upIDText.getText())) {
                        l = li;
                    }
                }
                if (l == null) {
                    JOptionPane.showMessageDialog(this, "Product ID not found", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                upIDText.setText(l.getProductID());
                upNameText.setText(l.getProductName());
                upCpNameText.setText(l.getProductCompany());
                upPriceText.setText(String.valueOf(l.getProductPrice()));
                upQntText.setText(String.valueOf(l.getProductQuantity()));
                upDateText.setText(l.getAddedDate());


            } catch (NullPointerException e) {
                logger.log(java.util.logging.Level.SEVERE, null, e);
                JOptionPane.showMessageDialog(this, "An error occurred while fetching product details", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_upIDTextKeyPressed

    private void adminLoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminLoginBtnActionPerformed
        // TODO add your handling code here:
        adminLogin();

    }//GEN-LAST:event_adminLoginBtnActionPerformed

    private void cartIDTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cartIDTxtKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cartIDTxt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Product ID", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else{
                JOptionPane.showMessageDialog(this, "Enter Quantity too", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_cartIDTxtKeyPressed



    private void cartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartBtnActionPerformed
        // TODO add your handling code here:
        String productId = cartIDTxt.getText().trim();
        String quantityStr = jTextField2.getText().trim();
        if (productId.isEmpty() || quantityStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both Product ID and Quantity", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{

            InventoryModel model = operations.findProductID(productId);
            if (model != null) {
                int quantity;
                try {
                    quantity = Integer.parseInt(quantityStr);
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(this, "Quantity must be a positive integer", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid quantity format", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (quantity > model.getProductQuantity()) {
                    JOptionPane.showMessageDialog(this, "Insufficient stock available", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LinkedList <InventoryModel> cartList = user.getUserCart();
                cartList.add(new InventoryModel(model.getProductID(), model.getProductName(), model.getProductCompany(), model.getProductPrice(), quantity, operations.getCurrentDate()));
                user.setUserCart(cartList);
                cartQueue.enqueue(model.getProductID(), model.getProductName(), model.getProductCompany(),model.getProductPrice(), quantity, "Pending");
                int rowCount = approvalTable.getRowCount();
                if(rowCount==size-1||approvalQueue.isFull()){
                    JOptionPane.showMessageDialog(this, "Approval Queue is full, please wait", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Structures.loadFromQueue(userCartTbl, cartQueue.getQueueData(), cartQueue.getFront(), cartQueue.getRear());

                grandTotalView.setText("Grand Total = " + operations.calculateTotalInventoryValue(cartList));
            }
        }
        catch (NullPointerException e){
            logger.log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Product ID not found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }//GEN-LAST:event_cartBtnActionPerformed

    private void selectAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAdminActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, "card7");
    }//GEN-LAST:event_selectAdminActionPerformed

    private void selectUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectUserActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, "card6");
    }//GEN-LAST:event_selectUserActionPerformed

    private void userViewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userViewBtnActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)userActionsPanel.getLayout();
        card.show(userActionsPanel, "card2");
        Structures.loadInventoryListToTable(userViewTbl, this.list);
    }//GEN-LAST:event_userViewBtnActionPerformed

    private void useCartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useCartBtnActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)userActionsPanel.getLayout();
        card.show(userActionsPanel, "card3");
        Structures.loadFromStack(userCartHistoryTbl, userCartHistStack.getStackData(), approvalHistStack.getTop());
        Structures.loadFromQueue(userCartTbl, cartQueue.getQueueData(), cartQueue.getFront(), cartQueue.getRear());

    }//GEN-LAST:event_useCartBtnActionPerformed

    private void userLoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userLoginBtnActionPerformed
        // TODO add your handling code here:
        userLogin();
    }//GEN-LAST:event_userLoginBtnActionPerformed

    private void adminAscSortBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminAscSortBtnActionPerformed
        // TODO add your handling code here:
        sortAction(viewTableAdmin, adminSortCmb, true);

    }//GEN-LAST:event_adminAscSortBtnActionPerformed

    private void searchAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAdminActionPerformed
        // TODO add your handling code here:
        searchAction(adminSearchTxt.getText(),viewTableAdmin, adminSearchCmb);

    }//GEN-LAST:event_searchAdminActionPerformed

    private void userPasswordTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userPasswordTxtFocusGained
        // TODO add your handling code here:
        userPasswordTxt.setText("");
    }//GEN-LAST:event_userPasswordTxtFocusGained

    private void adminPasswordTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adminPasswordTxtFocusGained
        // TODO add your handling code here:
        adminPasswordTxt.setText("");
    }//GEN-LAST:event_adminPasswordTxtFocusGained

    private void userUsernameTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userUsernameTxtFocusGained
        // TODO add your handling code here:
        userUsernameTxt.setText("");
    }//GEN-LAST:event_userUsernameTxtFocusGained

    private void adminUsernameTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adminUsernameTxtFocusGained
        // TODO add your handling code here:
        adminUsernameTxt.setText("");
    }//GEN-LAST:event_adminUsernameTxtFocusGained

    private void adminPasswordTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adminPasswordTxtKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            adminLogin();
        }
    }//GEN-LAST:event_adminPasswordTxtKeyPressed

    private void clearAdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAdminBtnActionPerformed
        // TODO add your handling code here:
        adminSearchTxt.setText("");
    }//GEN-LAST:event_clearAdminBtnActionPerformed

    private void adminSearchTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adminSearchTxtFocusGained
        // TODO add your handling code here:
        adminSearchTxt.setText("");
    }//GEN-LAST:event_adminSearchTxtFocusGained

    private void adminLogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminLogoutBtnActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, "card7");
        CardLayout card2 = (CardLayout)navPanel.getLayout();
        card2.show(navPanel, "card4");

    }//GEN-LAST:event_adminLogoutBtnActionPerformed

    private void userLogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userLogoutBtnActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, "card6");
        CardLayout card2 = (CardLayout)navPanel.getLayout();
        card2.show(navPanel, "card4");
    }//GEN-LAST:event_userLogoutBtnActionPerformed

    private void approvePanelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approvePanelBtnActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)adminActionsPanel.getLayout();
        card.show(adminActionsPanel, "card6");
        Structures.loadFromQueue(approvalTable,approvalQueue.getQueueData(), approvalQueue.getFront(), approvalQueue.getRear());
        Structures.loadFromStack(approvalHistoryTable, approvalHistStack.getStackData(), approvalHistStack.getTop());

    }//GEN-LAST:event_approvePanelBtnActionPerformed

    private void approveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveBtnActionPerformed
        // TODO add your handling code here:
        if (approvalQueue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items in approval queue", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(approvalHistStack.isFull()){
            JOptionPane.showMessageDialog(this, "Approval history is full, clear history to continue", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (userCartHistStack.isFull()){
            JOptionPane.showMessageDialog(this, "User cart history is full, clear history to continue", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (approvalQueue.getSize() + approvalHistStack.getTop() +1 > size){
            JOptionPane.showMessageDialog(this, "Not enough space in approval history to approve all items, clear history to continue", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            
            if (cartQueue.isEmpty()){
                JOptionPane.showMessageDialog(this, "All pending requests have been approved", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            else{
                String [] cart = cartQueue.peekFront();
                approvalHistStack.push(cart[0], cart[1], cart[2], Double.parseDouble(cart[3]), Integer.parseInt(cart[4]), operations.getCurrentDate());
                userCartHistStack.push(cart[0], cart[1], cart[2], Double.parseDouble(cart[3]), Integer.parseInt(cart[4]), "Approved");
                InventoryModel model = operations.findProductID(cart[0]);
                model.setProductQuantity(model.getProductQuantity() - Integer.parseInt(cart[4]));
                cartQueue.dequeue();
            }
            Structures.loadFromQueue(approvalTable, cartQueue.getQueueData(), cartQueue.getFront(), cartQueue.getRear());
            Structures.loadFromStack(approvalHistoryTable, approvalHistStack.getStackData(), approvalHistStack.getTop());
            Structures.loadFromQueue(userCartTbl, cartQueue.getQueueData(), cartQueue.getFront(), cartQueue.getRear());
            Structures.loadFromStack(userCartHistoryTbl, userCartHistStack.getStackData(), userCartHistStack.getTop());
        }
        catch (NullPointerException e){
            logger.log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "An error occurred during approval", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


    }//GEN-LAST:event_approveBtnActionPerformed

    private void adminApprovalClearAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminApprovalClearAllBtnActionPerformed
        // TODO add your handling code here:
        if (approvalHistStack.isEmpty()){
            JOptionPane.showMessageDialog(this, "Approval history is already empty", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        while (!approvalHistStack.isEmpty()) {
            approvalHistStack.pop();
        }
        Structures.loadFromStack(approvalHistoryTable, approvalHistStack.getStackData(), approvalHistStack.getTop());

    }//GEN-LAST:event_adminApprovalClearAllBtnActionPerformed

    private void adminApprovalClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminApprovalClearBtnActionPerformed
        // TODO add your handling code here:
        if (approvalHistStack.isEmpty()){
            JOptionPane.showMessageDialog(this, "Approval history is already empty", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        approvalHistStack.pop();
        Structures.loadFromStack(approvalHistoryTable, approvalHistStack.getStackData(), approvalHistStack.getTop());
    }//GEN-LAST:event_adminApprovalClearBtnActionPerformed

    private void clearUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearUserBtnActionPerformed
        // TODO add your handling code here:
        userSearchTxt1.setText("");
    }//GEN-LAST:event_clearUserBtnActionPerformed

    private void userSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userSearchBtnActionPerformed
        // TODO add your handling code here:
        searchAction(userSearchTxt1.getText(),userViewTbl, userSearchCmb1);

    }//GEN-LAST:event_userSearchBtnActionPerformed

    private void userSearchTxt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userSearchTxt1FocusGained
        // TODO add your handling code here:
        userSearchTxt1.setText("");
    }//GEN-LAST:event_userSearchTxt1FocusGained

    private void userAscSortBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userAscSortBtn1ActionPerformed
        // TODO add your handling code here:
        sortAction(userViewTbl, userSortCmb1, true);
    }//GEN-LAST:event_userAscSortBtn1ActionPerformed

    private void userDescSortBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userDescSortBtnActionPerformed
        // TODO add your handling code here:
        sortAction(userViewTbl, userSortCmb1, false);
    }//GEN-LAST:event_userDescSortBtnActionPerformed

    private void adminDescSortBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminDescSortBtnActionPerformed
        // TODO add your handling code here:
        sortAction(viewTableAdmin, adminSortCmb, false);
    }//GEN-LAST:event_adminDescSortBtnActionPerformed

    private void addNewClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewClearBtnActionPerformed
        // TODO add your handling code here:
        if (addNewStack.isEmpty()){
            JOptionPane.showMessageDialog(this, "Add new history is already empty", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        while (!addNewStack.isEmpty()) {
            addNewStack.pop();
        }
        Structures.loadFromStack(addNewTable, addNewStack.getStackData(), addNewStack.getTop());
    }//GEN-LAST:event_addNewClearBtnActionPerformed

    private void updateClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateClearBtnActionPerformed
        // TODO add your handling code here:
        if (updateStack.isEmpty()){
            JOptionPane.showMessageDialog(this, "Update history is already empty", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        while (!updateStack.isEmpty()) {
            updateStack.pop();
        }
        Structures.loadFromStack(updateTable, updateStack.getStackData(), updateStack.getTop());
    }//GEN-LAST:event_updateClearBtnActionPerformed

    private void approveAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveAllBtnActionPerformed
        // TODO add your handling code here:
        if (approvalQueue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items in approval queue", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(approvalHistStack.isFull()){
            JOptionPane.showMessageDialog(this, "Approval history is full, clear history to continue", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (userCartHistStack.isFull()){
            JOptionPane.showMessageDialog(this, "User cart history is full, clear history to continue", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (approvalQueue.getSize() + approvalHistStack.getTop() +1 > size){
            JOptionPane.showMessageDialog(this, "Not enough space in approval history to approve all items, clear history to continue", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            while (!approvalQueue.isEmpty()) {

                if (cartQueue.isEmpty()){
                    JOptionPane.showMessageDialog(this, "All pending requests have been approved", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else{
                    String [] cart = cartQueue.peekFront();
                    approvalHistStack.push(cart[0], cart[1], cart[2], Double.parseDouble(cart[3]), Integer.parseInt(cart[4]), operations.getCurrentDate());
                    userCartHistStack.push(cart[0], cart[1], cart[2], Double.parseDouble(cart[3]), Integer.parseInt(cart[4]), "Approved");
                    InventoryModel model = operations.findProductID(cart[0]);
                    model.setProductQuantity(model.getProductQuantity() - Integer.parseInt(cart[4]));
                    cartQueue.dequeue();
                }

            }
            Structures.loadFromQueue(approvalTable, cartQueue.getQueueData(), cartQueue.getFront(), cartQueue.getRear());
            Structures.loadFromStack(approvalHistoryTable, approvalHistStack.getStackData(), approvalHistStack.getTop());
            Structures.loadFromQueue(userCartTbl, cartQueue.getQueueData(), cartQueue.getFront(), cartQueue.getRear());
            Structures.loadFromStack(userCartHistoryTbl, userCartHistStack.getStackData(), userCartHistStack.getTop());
        }catch (NullPointerException e){
            logger.log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "An error occurred during approval", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }//GEN-LAST:event_approveAllBtnActionPerformed

    private void userClearCheckoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userClearCheckoutBtnActionPerformed
        // TODO add your handling code here:
        if(userCartHistStack.isEmpty()){
            JOptionPane.showMessageDialog(this, "Cart is already empty", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        while (!userCartHistStack.isEmpty()) {
            userCartHistStack.pop();
        }
        Structures.loadFromStack(userCartHistoryTbl, userCartHistStack.getStackData(), userCartHistStack.getTop());
    }//GEN-LAST:event_userClearCheckoutBtnActionPerformed

    private void yserCheckoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yserCheckoutBtnActionPerformed
        // TODO add your handling code here:
        if(cartQueue.isEmpty()){
            JOptionPane.showMessageDialog(this, "Cart is empty, add items to cart before checkout", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        LinkedList<InventoryModel> cart =user.getUserCart();
        for (InventoryModel inventoryModel : cart) {
            approvalQueue.enqueue(inventoryModel.getProductID(), inventoryModel.getProductName(),
                    inventoryModel.getProductCompany(), inventoryModel.getProductPrice(), inventoryModel.getProductQuantity(), operations.getCurrentDate());

        }
        Structures.loadFromQueue(approvalTable, approvalQueue.getQueueData(), approvalQueue.getFront(), approvalQueue.getRear());
        JOptionPane.showMessageDialog(this, "Checkout successful! Awaiting admin approval.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_yserCheckoutBtnActionPerformed

    private void clearDeleteHistoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearDeleteHistoryBtnActionPerformed
        // TODO add your handling code here:
        if(deleteStack.isEmpty()){
            JOptionPane.showMessageDialog(this, "Delete history is already empty", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        while (!deleteStack.isEmpty()) {
            deleteStack.pop();
        }
        Structures.loadFromStack(deleteTable, deleteStack.getStackData(), deleteStack.getTop());
    }//GEN-LAST:event_clearDeleteHistoryBtnActionPerformed

    private void userPasswordTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userPasswordTxtKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            userLogin();
        }
    }//GEN-LAST:event_userPasswordTxtKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel View;
    private javax.swing.JTextField addCpNameText;
    private javax.swing.JTextField addDateText;
    private javax.swing.JTextField addIDText;
    private javax.swing.JTextField addNameText;
    private javax.swing.JButton addNewBtn;
    private javax.swing.JButton addNewClearBtn;
    private javax.swing.JPanel addNewPanel;
    private javax.swing.JButton addNewPanelBtn;
    private javax.swing.JTable addNewTable;
    private javax.swing.JTextField addPriceText;
    private javax.swing.JTextField addQntText;
    private javax.swing.JPanel adminActionsPanel;
    private javax.swing.JButton adminApprovalClearAllBtn;
    private javax.swing.JButton adminApprovalClearBtn;
    private javax.swing.JButton adminAscSortBtn;
    private javax.swing.JButton adminDescSortBtn;
    private javax.swing.JButton adminLoginBtn;
    private javax.swing.JPanel adminLoginPanel;
    private javax.swing.JButton adminLogoutBtn;
    private javax.swing.JPanel adminNavPanel;
    private javax.swing.JPanel adminPanel;
    private javax.swing.JPasswordField adminPasswordTxt;
    private javax.swing.JComboBox<String> adminSearchCmb;
    private javax.swing.JTextField adminSearchTxt;
    private javax.swing.JComboBox<String> adminSortCmb;
    private javax.swing.JTextField adminUsernameTxt;
    private javax.swing.JTable approvalHistoryTable;
    private javax.swing.JTable approvalTable;
    private javax.swing.JButton approveAllBtn;
    private javax.swing.JButton approveBtn;
    private javax.swing.JPanel approvePanel;
    private javax.swing.JButton approvePanelBtn;
    private javax.swing.JButton cartBtn;
    private javax.swing.JTextField cartIDTxt;
    private javax.swing.JButton clearAdminBtn;
    private javax.swing.JButton clearDeleteHistoryBtn;
    private javax.swing.JButton clearUserBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel deleteLabel;
    private javax.swing.JPanel deletePanel;
    private javax.swing.JButton deletePanelBtn;
    private javax.swing.JTable deleteTable;
    private javax.swing.JTextField deleteText;
    private javax.swing.JTable deleteViewTable;
    private javax.swing.JLabel grandTotalView;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel navPanel;
    private javax.swing.JButton searchAdmin;
    private javax.swing.JButton selectAdmin;
    private javax.swing.JPanel selectPanel;
    private javax.swing.JButton selectUser;
    private javax.swing.JLabel upCpNameLabel;
    private javax.swing.JTextField upCpNameText;
    private javax.swing.JLabel upDateLabel;
    private javax.swing.JTextField upDateText;
    private javax.swing.JTextField upIDText;
    private javax.swing.JLabel upIDlabel;
    private javax.swing.JLabel upNameLabel;
    private javax.swing.JTextField upNameText;
    private javax.swing.JLabel upPriceLabel;
    private javax.swing.JTextField upPriceText;
    private javax.swing.JLabel upQntLabel;
    private javax.swing.JTextField upQntText;
    private javax.swing.JButton updateBtn;
    private javax.swing.JButton updateClearBtn;
    private javax.swing.JPanel updatePanel;
    private javax.swing.JButton updatePanelBtn;
    private javax.swing.JTable updateTable;
    private javax.swing.JButton useCartBtn;
    private javax.swing.JPanel userActionsPanel;
    private javax.swing.JButton userAscSortBtn1;
    private javax.swing.JTable userCartHistoryTbl;
    private javax.swing.JPanel userCartPanel;
    private javax.swing.JTable userCartTbl;
    private javax.swing.JButton userClearCheckoutBtn;
    private javax.swing.JButton userDescSortBtn;
    private javax.swing.JButton userLoginBtn;
    private javax.swing.JPanel userLoginPanel;
    private javax.swing.JButton userLogoutBtn;
    private javax.swing.JPanel userNavPanel;
    private javax.swing.JPanel userPanel;
    private javax.swing.JPasswordField userPasswordTxt;
    private javax.swing.JButton userSearchBtn;
    private javax.swing.JComboBox<String> userSearchCmb1;
    private javax.swing.JTextField userSearchTxt1;
    private javax.swing.JComboBox<String> userSortCmb1;
    private javax.swing.JTextField userUsernameTxt;
    private javax.swing.JButton userViewBtn;
    private javax.swing.JPanel userViewPanel;
    private javax.swing.JTable userViewTbl;
    private javax.swing.JButton viewAllBtn;
    private javax.swing.JTable viewTableAdmin;
    private javax.swing.JButton yserCheckoutBtn;
    // End of variables declaration//GEN-END:variables
}

