/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.controllers;

import bangnn.dtos.ItemsDTO;
import bangnn.dtos.SuppliersDTO;
import bangnn.localization.impl.GenericLocalizationImpl;
import bangnn.service.impl.ItemServiceImpl;
import bangnn.service.impl.SupplierServiceImpl;
import bangnn.sort.impl.ItemsSortHelper;
import bangnn.sort.impl.SuppliersSortHelper;
import bangnn.utils.CSVExportHelper;
import bangnn.utils.CSVImportHelper;
import bangnn.utils.MotionPanel;
import java.awt.Event;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author bangmaple
 */
public class SupplierController extends javax.swing.JFrame {

    private final class SupplierControllerComponentsLocalizationImpl extends GenericLocalizationImpl {

        private ResourceBundle resource;

        public SupplierControllerComponentsLocalizationImpl() {
            super.init();
        }

        @Override
        public void loadLocalizationProperties() {
            this.resource = ResourceBundle
                    .getBundle("localization/SupplierController", super.getLocale());

        }

        public ResourceBundle getResource() {
            return resource;
        }

        public void setResource(ResourceBundle resource) {
            this.resource = resource;
        }

    }

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(SupplierController.class);

    private static String SUCCESSFULLY_SAVED_SUPPLIER;
    private static String SUCCESSFULLY_DELETED_SUPPLIER;
    private static String SUCCESSFULLY_SAVED_ITEM;
    private static String SUCCESSFULLY_DELETED_ITEM;
    private static String LOAD_SUPPLIERS_FAILED;
    private static String LOAD_ITEMS_FAILED;
    private static String SAVE_ITEM_FAILED;
    private static String SAVE_SUPPLIER_FAILED;
    private static String DELETE_ITEM_FAILED;
    private static String DELETE_SUPPLIER_FAILED;
    private static String DELETE_OR_UPDATE_ITEM_FAILED_CHOOSE;
    private static String DELETE_OR_UPDATE_SUPPLIER_FAILED_CHOOSE;
    private static String ITEM_PRICE_INVALID;
    private static String GET_ITEM_FAILED;
    private static String ASK_BEFORE_DELETING_ITEM;
    private static String ASK_BEFORE_DELETING_SUPPLIER;
    private static String ASK_BEFORE_EXIT_PROGRAM;
    private static String CONFIRMATION;
    private static String GLOBAL_ERROR;

    private static String ITEM_CODE_LABEL;
    private static String ITEM_NAME_LABEL;
    private static String ITEM_PRICE_LABEL;
    private static String ITEM_UNIT_LABEL;
    private static String ITEM_IS_SUPPLYING_LABEL;
    private static String ITEM_SUPPLIER_LABEL;
    private static String ADD_NEW_LABEL;
    private static String SAVE_LABEL;
    private static String DELETE_LABEL;
    private static String QUIT_PROGRAM_LABEL;

    private static String SUPPLIER_CODE_LABEL;
    private static String SUPPLIER_NAME_LABEL;
    private static String SUPPLIER_ADDRESS_LABEL;
    private static String SUPPLIER_IS_COLLOBORATING;

    static {
        IconFontSwing.register(FontAwesome.getIconFont());
    }

    public static void loadInternationalizationResources(ResourceBundle resource) {
        SUCCESSFULLY_SAVED_SUPPLIER = resource.getString("SUCCESSFULLY_SAVED_SUPPLIER");
        SUCCESSFULLY_DELETED_SUPPLIER = resource.getString("SUCCESSFULLY_DELETED_SUPPLIER");
        SUCCESSFULLY_SAVED_ITEM = resource.getString("SUCCESSFULLY_SAVED_ITEM");
        SUCCESSFULLY_DELETED_ITEM = resource.getString("SUCCESSFULLY_DELETED_ITEM");
        LOAD_SUPPLIERS_FAILED = resource.getString("LOAD_SUPPLIERS_FAILED");
        LOAD_ITEMS_FAILED = resource.getString("LOAD_ITEMS_FAILED");
        SAVE_ITEM_FAILED = resource.getString("SAVE_ITEM_FAILED");
        SAVE_SUPPLIER_FAILED = resource.getString("SAVE_SUPPLIER_FAILED");
        DELETE_ITEM_FAILED = resource.getString("DELETE_ITEM_FAILED");
        DELETE_SUPPLIER_FAILED = resource.getString("DELETE_SUPPLIER_FAILED");
        DELETE_OR_UPDATE_ITEM_FAILED_CHOOSE = resource
                .getString("DELETE_OR_UPDATE_ITEM_FAILED_CHOOSE");
        DELETE_OR_UPDATE_SUPPLIER_FAILED_CHOOSE = resource
                .getString("DELETE_OR_UPDATE_SUPPLIER_FAILED_CHOOSE");
        ITEM_PRICE_INVALID = resource.getString("ITEM_PRICE_INVALID");
        GET_ITEM_FAILED = resource.getString("GET_ITEM_FAILED");
        ASK_BEFORE_DELETING_ITEM = resource.getString("ASK_BEFORE_DELETING_ITEM");
        ASK_BEFORE_DELETING_SUPPLIER = resource.getString("ASK_BEFORE_DELETING_SUPPLIER");
        ASK_BEFORE_EXIT_PROGRAM = resource.getString("ASK_BEFORE_EXIT_PROGRAM");
        CONFIRMATION = resource.getString("CONFIRMATION");
        GLOBAL_ERROR = resource.getString("GLOBAL_ERROR");
        loadComponentInternationalizationResources(resource);
    }

    private static void loadComponentInternationalizationResources(ResourceBundle resource) {
        ITEM_CODE_LABEL = resource.getString("ITEM_CODE_LABEL");
        ITEM_NAME_LABEL = resource.getString("ITEM_NAME_LABEL");
        ITEM_PRICE_LABEL = resource.getString("ITEM_PRICE_LABEL");
        ITEM_UNIT_LABEL = resource.getString("ITEM_UNIT_LABEL");
        ITEM_IS_SUPPLYING_LABEL = resource.getString("ITEM_IS_SUPPLYING_LABEL");
        ITEM_SUPPLIER_LABEL = resource.getString("ITEM_SUPPLIER_LABEL");
        ADD_NEW_LABEL = resource.getString("ADD_NEW_LABEL");
        SAVE_LABEL = resource.getString("SAVE_LABEL");
        DELETE_LABEL = resource.getString("DELETE_LABEL");
        QUIT_PROGRAM_LABEL = resource.getString("QUIT_PROGRAM_LABEL");
        SUPPLIER_CODE_LABEL = resource.getString("SUPPLIER_CODE_LABEL");
        SUPPLIER_NAME_LABEL = resource.getString("SUPPLIER_NAME_LABEL");
        SUPPLIER_ADDRESS_LABEL = resource.getString("SUPPLIER_ADDRESS_LABEL");
        SUPPLIER_IS_COLLOBORATING = resource.getString("SUPPLIER_IS_COLLOBORATING");
    }

    private DefaultTableModel suppliersTable;
    private DefaultTableModel itemsTable;
    private final SupplierServiceImpl supplierService = new SupplierServiceImpl();
    private final ItemServiceImpl itemService = new ItemServiceImpl();

    private final ItemsSortHelper itemsSort = new ItemsSortHelper();
    private final SuppliersSortHelper suppliersSort = new SuppliersSortHelper();

    private final CSVImportHelper importDataFromCSV = new CSVImportHelper();
    private final CSVExportHelper exportDataToCSV = new CSVExportHelper();

    private SupplierControllerComponentsLocalizationImpl supplierComponentsLocalization;

    private String itemSearchData = "";
    private String supplierSearchData = "";

    public SupplierController() {
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        initComponents();
        initModel();
        supplierComponentsLocalization
                = new SupplierControllerComponentsLocalizationImpl();
        supplierComponentsLocalization.loadLocalizationProperties();

        ResourceBundle resource = supplierComponentsLocalization.getResource();
        setComponentsInternationalizationResources(resource);
    }

    private void setComponentsInternationalizationResources(ResourceBundle resource) {
        btnQuitProgram1.setText(resource.getString("QUIT_PROGRAM_LABEL"));
        btnQuitProgram2.setText(resource.getString("QUIT_PROGRAM_LABEL"));
        lblCode.setText(resource.getString("SUPPLIER_CODE_LABEL"));
        lblName.setText(resource.getString("SUPPLIER_NAME_LABEL"));
        lblAddress.setText(resource.getString("SUPPLIER_ADDRESS_LABEL"));
        lblColloborating.setText(resource.getString("SUPPLIER_IS_COLLOBORATING"));
        lblItemCode.setText(resource.getString("ITEM_CODE_LABEL"));
        lblItemName.setText(resource.getString("ITEM_NAME_LABEL"));
        lblSuppliers.setText(resource.getString("ITEM_SUPPLIER_LABEL"));
        lblUnit.setText(resource.getString("ITEM_UNIT_LABEL"));
        lblPrice.setText(resource.getString("ITEM_PRICE_LABEL"));
        lblItemIsSupplying.setText(resource.getString("ITEM_IS_SUPPLYING_LABEL"));
        btnAddNewSupplier.setText(resource.getString("ADD_NEW_LABEL"));
        btnSaveSupplier.setText(resource.getString("SAVE_LABEL"));
        btnDeleteSupplier.setText(resource.getString("DELETE_LABEL"));
        btnAddNewItem.setText(resource.getString("ADD_NEW_LABEL"));
        btnSaveItem.setText(resource.getString("SAVE_LABEL"));
        btnDeleteItem.setText(resource.getString("DELETE_LABEL"));
        btnExportCSVItems.setText(resource.getString("EXPORT_TO_CSV"));
        btnExportToCSVSuppliers.setText(resource.getString("EXPORT_TO_CSV"));
        cbSortByItems.removeAllItems();

        cbSortByItems.setModel(new DefaultComboBoxModel<>(new String[]{resource.getString("ITEM_SORT_BY_CODE"),
            resource.getString("ITEM_SORT_BY_NAME"),
            resource.getString("ITEM_SORT_BY_SUPPLIER"),
            resource.getString("ITEM_SORT_BY_UNIT"),
            resource.getString("ITEM_SORT_BY_PRICE"),
            resource.getString("ITEM_SORT_BY_SUPPLYING")}));
        cbSortBySupplier.removeAllItems();
        cbSortBySupplier.setModel(new DefaultComboBoxModel<>(new String[]{resource.getString("SUPPLIER_SORT_BY_CODE"),
            resource.getString("SUPPLIER_SORT_BY_NAME"),
            resource.getString("SUPPLIER_SORT_BY_ADDRESS")
        }));
        cbLanguages.removeAllItems();
        cbLanguages.setModel(new DefaultComboBoxModel<>(new String[]{"Tiếng Việt", "Tiếng Anh"}));
        btnChangeMode.setText("Đổi sang chế độ màu khác");
        cbLanguages.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String lang = String.valueOf(e.getItem());
                    if (lang.equals("Tiếng Anh") || lang.equals("English")) {
                        try {
                            FileWriter fw = new FileWriter(getClass().getResourceAsStream("/language.properties").toString());
                            fw.write("language = en-US");
                            fw.close();
                            dispose();
                            MainController.init();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private void initModel() {
        suppliersTable = (DefaultTableModel) this.tblSuppliers.getModel();
        itemsTable = (DefaultTableModel) this.tblItems.getModel();
        loadSuppliers();
        loadItems(true);
    }

    private void clearTable(JTable table, DefaultTableModel tableModel) {
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                table.setValueAt("", i, j);
            }
        }
        tableModel.setRowCount(0);
    }

    private void loadItems(boolean loadable) {
        clearTable(tblItems, itemsTable);
        try {
            itemService.getItems().stream().forEach((item) -> {
                itemsTable.addRow(new Object[]{item.getItemCode(), item.getItemName(),
                    item.getSupplier(), item.getUnit(), String.format("%.2f",
                    item.getPrice()), item.isSupplying() ? "TRUE" : "FALSE"});
            });
        } catch (NullPointerException e) {
            log.atError().log(GLOBAL_ERROR + e.getMessage());
            JOptionPane.showMessageDialog(this, LOAD_ITEMS_FAILED);
        }
        if (loadable) {
            loadItemSupplierComboBoxData();
        }
    }

    private void loadItemSupplierComboBoxData() {
        this.cbSuppliers.removeAllItems();
        try {
            List<SuppliersDTO> list = supplierService.getSuppliers();
            list.stream().forEach((supplier) -> {
                if (supplier.isColloborating()) {
                    this.cbSuppliers.addItem(supplier.getSupCode() + "-" + supplier.getSupName());
                }
            });
        } catch (NullPointerException e) {
            log.atError().log(GLOBAL_ERROR + e.getMessage());
            JOptionPane.showMessageDialog(this, LOAD_SUPPLIERS_FAILED);
        }
    }

    private void loadSuppliers() {
        clearTable(tblSuppliers, suppliersTable);
        try {
            List<SuppliersDTO> list = supplierService.getSuppliers();
            list.stream().forEach((supplier) -> {
                suppliersTable.addRow(new Object[]{supplier.getSupCode(),
                    supplier.getSupName(), supplier.getAddress()});
            });
        } catch (NullPointerException e) {
            log.atError().log(GLOBAL_ERROR + e.getMessage());
            JOptionPane.showMessageDialog(this, LOAD_SUPPLIERS_FAILED);
        }
    }

    private SuppliersDTO formSupplierDTO(final String supCode, final String supName,
            final String address, final boolean colloborating) {
        SuppliersDTO supplier = new SuppliersDTO(supCode, supName, address, colloborating);
        return supplier;
    }

    private void showSuccessfullySavedSupplierMsg() {
        loadItemSupplierComboBoxData();
        JOptionPane.showMessageDialog(null, SUCCESSFULLY_SAVED_SUPPLIER);
    }

    private void saveSupplierViaService(final String supCode, final String supName,
            final String address, final boolean colloborating) {
        SuppliersDTO supplier = formSupplierDTO(supCode, supName,
                address, colloborating);
        try {
            if (this.txtCode.isEditable()) {
                supplierService.createSupplier(supplier);
                newItem();
            } else {
                supplierService.updateSupplier(supplier, supCode);
            }
            showSuccessfullySavedSupplierMsg();
            loadItems(true);
        } catch (IllegalArgumentException | NullPointerException e) {
            this.chkColloborating.setSelected(true);
            log.atError().log(GLOBAL_ERROR + e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage());
            JOptionPane.showMessageDialog(this, SAVE_SUPPLIER_FAILED);
        }

    }

    private void saveSupplier() {
        final String supCode = this.txtCode.getText();
        final String supName = this.txtName.getText();
        final String address = this.txtAddress.getText();
        final boolean colloborating = this.chkColloborating.isSelected();
        saveSupplierViaService(supCode, supName, address, colloborating);
    }

    private void newSupplier() {
        this.txtCode.setText("");
        this.txtCode.setEditable(true);
        this.txtName.setText("");
        this.chkColloborating.setSelected(false);
        this.txtAddress.setText("");
    }

    private void finalizeDeletingSupplier() {
        JOptionPane.showMessageDialog(null, SUCCESSFULLY_DELETED_SUPPLIER);
        loadSuppliers();
        loadItemSupplierComboBoxData();
    }

    private void deleteSupplier() {
        if (!this.txtCode.isEditable()) {
            final String code = this.txtCode.getText();
            try {
                supplierService.deleteSupplier(code);
                finalizeDeletingSupplier();
            } catch (NullPointerException | IllegalArgumentException e) {
                log.atError().log(GLOBAL_ERROR + e.getMessage());
                JOptionPane.showMessageDialog(this, e.getMessage());
                JOptionPane.showMessageDialog(this, DELETE_SUPPLIER_FAILED);
            }
        } else {
            JOptionPane.showMessageDialog(this, DELETE_OR_UPDATE_SUPPLIER_FAILED_CHOOSE);
        }
    }

    private boolean askBeforeDeletingSupplier() {
        return JOptionPane.showConfirmDialog(this, ASK_BEFORE_DELETING_SUPPLIER) == 0;
    }

    private void sortSuppliersAscending() {
        final int index = cbSortBySupplier.getSelectedIndex();
        suppliersSort.sortAscending(suppliersTable.getDataVector(), index);
        this.suppliersTable.fireTableDataChanged();
    }

    private void sortSuppliersDescending() {
        final int index = cbSortBySupplier.getSelectedIndex();
        suppliersSort.sortDescending(suppliersTable.getDataVector(), index);
        this.suppliersTable.fireTableDataChanged();
    }

    private void confirmQuitProgram() {
        int choice = JOptionPane.showConfirmDialog(this, ASK_BEFORE_EXIT_PROGRAM,
                "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        if (choice == 0) {
            System.exit(0);
        }
    }

    private void exportSuppliersDataToCSV() {
        try {
            exportDataToCSV.writeSuppliersData(supplierService.getSuppliers());
        } catch (IOException | NullPointerException e) {
            log.atError().log(GLOBAL_ERROR + e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void parseSupplierDataToShow(final SuppliersDTO supplier) {
        this.txtCode.setText(supplier.getSupCode());
        this.txtCode.setEditable(false);
        this.txtName.setText(supplier.getSupName());
        this.txtAddress.setText(supplier.getAddress());
        this.chkColloborating.setSelected(supplier.isColloborating());
    }

    private ItemsDTO formItemDTO() {
        final String itemCode = this.txtItemCode.getText();
        final String itemName = this.txtItemName.getText();
        final String itemSupplier = this.cbSuppliers.getItemAt(cbSuppliers
                .getSelectedIndex()).split("-")[0];
        final String itemUnit = this.txtItemUnit.getText();
        Double itemPrice;
        try {
            itemPrice = Double.parseDouble(this.txtItemPrice.getText());
        } catch (NumberFormatException e) {
            log.atError().log(GLOBAL_ERROR + e.getMessage());
            JOptionPane.showMessageDialog(this, SAVE_ITEM_FAILED);
            return null;
        }
        final boolean itemIsSupplying = chkColloborating.isSelected();
        ItemsDTO dto = new ItemsDTO();
        dto.setItemCode(itemCode);
        dto.setItemName(itemName);
        dto.setUnit(itemUnit);
        dto.setPrice(itemPrice.floatValue());
        dto.setSupplying(itemIsSupplying);
        dto.setSupplier(itemSupplier);
        return dto;
    }

    private void newItem() {
        txtItemCode.setText("");
        txtItemCode.setEditable(true);
        txtItemName.setText("");
        txtItemUnit.setText("");
        txtItemPrice.setText("");
        chkColloborating.setSelected(false);
        cbSuppliers.setSelectedIndex(0);
    }

    private void createItem(final ItemsDTO item) {
        itemService.createItem(item);
        JOptionPane.showMessageDialog(this, SUCCESSFULLY_SAVED_ITEM);
        loadItems(true);
    }

    private void updateItem(final ItemsDTO item) {
        final int itemSupplierIndex = this.cbSuppliers.getSelectedIndex();
        itemService.updateItem(item, item.getItemCode());
        this.cbSuppliers.setSelectedIndex(itemSupplierIndex);
        JOptionPane.showMessageDialog(this, SUCCESSFULLY_SAVED_ITEM);
        loadItems(false);
    }

    private void saveItem() {
        try {
            ItemsDTO dto = Objects.requireNonNull(formItemDTO());
            if (txtItemCode.isEditable()) {
                createItem(dto);
            } else {
                updateItem(dto);
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            log.atError().log(GLOBAL_ERROR + e.getMessage());
            JOptionPane.showMessageDialog(this, SAVE_ITEM_FAILED);
        }

    }

    private void removeItem() {
        if (!this.txtItemCode.isEditable()) {
            final String itemCode = this.txtItemCode.getText();
            try {
                itemService.deleteItem(itemCode);
            } catch (IllegalArgumentException e) {
                log.atError().log(GLOBAL_ERROR + e.getMessage());
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            JOptionPane.showMessageDialog(this, SUCCESSFULLY_DELETED_ITEM);
            loadItems(true);
        } else {
            JOptionPane.showMessageDialog(this, DELETE_OR_UPDATE_ITEM_FAILED_CHOOSE);
        }

    }

    private String trimFullTextSearchString(String search,
            java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == Event.BACK_SPACE && search.length() > 0) {
            search = search.substring(0, search.length() - 1).trim();
        }
        search += String.valueOf(evt.getKeyChar()).trim();
        return search;
    }

    private void displaySuppliersFullTextSearchResult() {
        supplierService.findSuppliersByAnyChar(supplierSearchData)
                .stream().forEach((supplier) -> {
                    suppliersTable.addRow(new Object[]{supplier.getSupCode(),
                        supplier.getSupName(), supplier.getAddress()});
                });
    }

    private void initSuppliersFullTextSearch(java.awt.event.KeyEvent evt) {
        supplierSearchData = trimFullTextSearchString(itemSearchData, evt);
        clearTable(tblSuppliers, suppliersTable);
        displaySuppliersFullTextSearchResult();
    }

    private void displayItemsFullTextSearchResult() {
        itemService.findItemsByAnyChar(itemSearchData)
                .stream().forEach((item) -> {
                    itemsTable.addRow(new Object[]{item.getItemCode(),
                        item.getItemName(), item.getSupplier(), item.getUnit(),
                        String.format("%.2f", item.getPrice()),
                        item.isSupplying() ? "TRUE" : "FALSE"});
                });
    }

    private void initItemsFullTextSearch(java.awt.event.KeyEvent evt) {
        itemSearchData = trimFullTextSearchString(itemSearchData, evt);
        clearTable(tblItems, itemsTable);
        displayItemsFullTextSearchResult();
    }

    private void sortItemsASC() {
        final int index = cbSortByItems.getSelectedIndex();
        itemsSort.sortAscending(itemsTable.getDataVector(), index);
        this.itemsTable.fireTableDataChanged();
    }

    private void sortItemsDSC() {
        final int index = this.cbSortByItems.getSelectedIndex();
        itemsSort.sortDescending(itemsTable.getDataVector(), index);
        this.itemsTable.fireTableDataChanged();
    }

    private void exportItemsDataToCSV() {
        try {
            exportDataToCSV.writeItemsData(itemService.getItems());
        } catch (IOException e) {
            log.atError().log(GLOBAL_ERROR + e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void initLoadSupplierRowToFields() {
        int index = this.tblSuppliers.getSelectedRow();
        String supCode = String.valueOf(this.suppliersTable.getValueAt(index, 0));
        SuppliersDTO supplier = null;
        try {
            supplier = supplierService.getSupplier(supCode);
        } catch (NullPointerException | IllegalArgumentException e) {
            log.atError().log(GLOBAL_ERROR + e.getMessage());
            JOptionPane.showMessageDialog(this, GET_ITEM_FAILED);
        }
        if (supplier != null) {
            parseSupplierDataToShow(supplier);
        }
    }

    private void parseItemDataToShow(final ItemsDTO item) {
        this.txtItemCode.setEditable(false);
        this.txtItemCode.setText(item.getItemCode());
        this.txtItemName.setText(item.getItemName());
        this.txtItemPrice.setText(String.format("%.2f", item.getPrice()));
        this.txtItemUnit.setText(item.getUnit());
        this.chkItemSupplying.setSelected(item.isSupplying());
        final int suppliersSize = this.cbSuppliers.getItemCount();
        for (int i = 0; i < suppliersSize; i++) {
            final String supplierCode = item.getSupplier();
            final String supplierCodeFromCombobox = String.valueOf(cbSuppliers
                    .getItemAt(i)).split("-")[0];
            if (supplierCode.equals(supplierCodeFromCombobox)) {
                this.cbSuppliers.setSelectedIndex(i);
            }
        }
    }

    private ItemsDTO formItemDTO(final int index) {
        final String itemCode = String.valueOf(this.itemsTable.getValueAt(index, 0));
        final String itemName = String.valueOf(itemsTable.getValueAt(index, 1));
        final String supCode = String.valueOf(itemsTable.getValueAt(index, 2)).split("-")[0];
        final String unit = String.valueOf(itemsTable.getValueAt(index, 3));
        final Float price = Float.parseFloat(String.valueOf(itemsTable.getValueAt(index, 4)));
        final boolean supplying = String.valueOf(itemsTable.getValueAt(index, 5)).equals("TRUE");
        return new ItemsDTO(itemCode, itemName, unit, price, supplying, supCode);
    }

    private ItemsDTO getItemFromTable() {
        int index = this.tblItems.getSelectedRow();
        return formItemDTO(index);
    }

    private void initLoadItemsRowDataToFields() {
        ItemsDTO item = getItemFromTable();
        parseItemDataToShow(item);
    }

    private boolean askBeforeDeleting() {
        return JOptionPane.showConfirmDialog(this, ASK_BEFORE_DELETING_ITEM,
                CONFIRMATION, JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void start() {
        try {
            javax.swing.UIManager.setLookAndFeel(
                    new MaterialLookAndFeel(new JMarsDarkTheme()));
        } catch (UnsupportedLookAndFeelException ex) {
            log.atError().log("Error at SupplierController: Fail to load LookAndFeel theme.");
        }
        java.awt.EventQueue.invokeLater(() -> {
            new SupplierController().setVisible(true);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new MotionPanel(this);
        tabbedPane = new javax.swing.JTabbedPane();
        pnlSuppliers = new MotionPanel(this);
        btnDeleteSupplier = new javax.swing.JButton();
        chkColloborating = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        btnSortASCSupplier = new javax.swing.JButton();
        btnExportToCSVSuppliers = new javax.swing.JButton();
        btnSortDSCSupplier = new javax.swing.JButton();
        cbSortBySupplier = new javax.swing.JComboBox<>();
        btnSaveSupplier = new javax.swing.JButton();
        txtSearchSupplier = new javax.swing.JTextField();
        lblSearchSupplier = new javax.swing.JButton();
        btnQuitProgram1 = new javax.swing.JButton();
        btnAddNewSupplier = new javax.swing.JButton();
        lblAddress = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtCode = new javax.swing.JTextField();
        lblColloborating = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSuppliers = new javax.swing.JTable();
        lblCode = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        pnlItems = new MotionPanel(this);
        lblItemCode = new javax.swing.JLabel();
        lblUnit = new javax.swing.JLabel();
        lblItemName = new javax.swing.JLabel();
        lblSuppliers = new javax.swing.JLabel();
        txtItemCode = new javax.swing.JTextField();
        txtItemName = new javax.swing.JTextField();
        cbSuppliers = new javax.swing.JComboBox<>();
        txtItemUnit = new javax.swing.JTextField();
        lblPrice = new javax.swing.JLabel();
        txtItemPrice = new javax.swing.JTextField();
        btnAddNewItem = new javax.swing.JButton();
        btnSaveItem = new javax.swing.JButton();
        btnDeleteItem = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnQuitProgram2 = new javax.swing.JButton();
        btnExportCSVItems = new javax.swing.JButton();
        btnSortDSCItems = new javax.swing.JButton();
        lblSearchItems = new javax.swing.JButton();
        cbSortByItems = new javax.swing.JComboBox<>();
        btnSortASCItems = new javax.swing.JButton();
        txtSearchItems = new javax.swing.JTextField();
        lblItemIsSupplying = new javax.swing.JLabel();
        chkItemSupplying = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItems = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblLanguage = new javax.swing.JLabel();
        cbLanguages = new javax.swing.JComboBox<>();
        lblMode = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnChangeMode = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlSuppliers.setPreferredSize(new java.awt.Dimension(914, 458));

        btnDeleteSupplier.setText("Delete");
        btnDeleteSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSupplierActionPerformed(evt);
            }
        });
        btnDeleteSupplier.setIcon(IconFontSwing.buildIcon(FontAwesome.TRASH_O, 20));

        btnSortASCSupplier.setIcon(IconFontSwing.buildIcon(FontAwesome.ARROW_UP, 20));
        btnSortASCSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortASCSupplierActionPerformed(evt);
            }
        });

        btnExportToCSVSuppliers.setText("Export to CSV Format");
        btnExportToCSVSuppliers.setIcon(IconFontSwing.buildIcon(FontAwesome.DOWNLOAD, 20));
        btnExportToCSVSuppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportToCSVSuppliersActionPerformed(evt);
            }
        });

        btnSortDSCSupplier.setIcon(IconFontSwing.buildIcon(FontAwesome.ARROW_DOWN, 20));
        btnSortDSCSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortDSCSupplierActionPerformed(evt);
            }
        });

        cbSortBySupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sort By Code", "Sort By Name", "Sort By Address" }));

        btnSaveSupplier.setText("Save");
        btnSaveSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSupplierActionPerformed(evt);
            }
        });
        btnSaveSupplier.setIcon(IconFontSwing.buildIcon(FontAwesome.FLOPPY_O, 20));

        txtSearchSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSupplierActionPerformed(evt);
            }
        });
        txtSearchSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchSupplierKeyPressed(evt);
            }
        });

        lblSearchSupplier.setIcon(IconFontSwing.buildIcon(FontAwesome.SEARCH, 20));

        btnQuitProgram1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        btnQuitProgram1.setText("Quit Program");
        btnQuitProgram1.setIcon(IconFontSwing.buildIcon(FontAwesome.ARROW_RIGHT, 30));
        btnQuitProgram1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitProgram1ActionPerformed(evt);
            }
        });

        btnAddNewSupplier.setText("Add new");
        btnAddNewSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewSupplierActionPerformed(evt);
            }
        });
        btnAddNewSupplier.setIcon(IconFontSwing.buildIcon(FontAwesome.FILES_O, 20));

        lblAddress.setText("Address:");

        lblColloborating.setText("Colloborating:");

        tblSuppliers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSuppliers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSuppliersMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSuppliers);
        if (tblSuppliers.getColumnModel().getColumnCount() > 0) {
            tblSuppliers.getColumnModel().getColumn(0).setResizable(false);
            tblSuppliers.getColumnModel().getColumn(1).setResizable(false);
            tblSuppliers.getColumnModel().getColumn(2).setResizable(false);
        }

        lblCode.setText("Code:");

        lblName.setText("Name:");

        javax.swing.GroupLayout pnlSuppliersLayout = new javax.swing.GroupLayout(pnlSuppliers);
        pnlSuppliers.setLayout(pnlSuppliersLayout);
        pnlSuppliersLayout.setHorizontalGroup(
            pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuppliersLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSuppliersLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlSuppliersLayout.createSequentialGroup()
                                    .addGap(7, 7, 7)
                                    .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlSuppliersLayout.createSequentialGroup()
                                            .addComponent(btnAddNewSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnSaveSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnDeleteSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSuppliersLayout.createSequentialGroup()
                                            .addGap(9, 9, 9)
                                            .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(btnQuitProgram1)
                                                .addGroup(pnlSuppliersLayout.createSequentialGroup()
                                                    .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(lblColloborating)
                                                        .addComponent(lblAddress))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(chkColloborating)
                                                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(pnlSuppliersLayout.createSequentialGroup()
                                                    .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(lblName)
                                                        .addComponent(lblCode))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGap(18, 18, 18))))
                                .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlSuppliersLayout.createSequentialGroup()
                                        .addComponent(lblSearchSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSearchSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlSuppliersLayout.createSequentialGroup()
                                        .addComponent(btnSortASCSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSortDSCSupplier)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbSortBySupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSuppliersLayout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(pnlSuppliersLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btnExportToCSVSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))))
        );
        pnlSuppliersLayout.setVerticalGroup(
            pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuppliersLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName))
                .addGap(18, 18, 18)
                .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddress)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblColloborating)
                    .addComponent(chkColloborating))
                .addGap(18, 18, 18)
                .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNewSupplier)
                    .addComponent(btnSaveSupplier)
                    .addComponent(btnDeleteSupplier))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSuppliersLayout.createSequentialGroup()
                        .addComponent(btnSortASCSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblSearchSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSuppliersLayout.createSequentialGroup()
                        .addGroup(pnlSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSortBySupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSortDSCSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtSearchSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnExportToCSVSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnQuitProgram1)
                .addGap(25, 25, 25))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Suppliers", pnlSuppliers);

        lblItemCode.setText("Item Code:");

        lblUnit.setText("Unit:");

        lblItemName.setText("Item Name:");

        lblSuppliers.setText("Suppliers:");

        lblPrice.setText("Price:");

        btnAddNewItem.setText("Add New");
        btnAddNewItem.setIcon(IconFontSwing.buildIcon(FontAwesome.FILES_O, 20));
        btnAddNewItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewItemActionPerformed(evt);
            }
        });

        btnSaveItem.setText("Save");
        btnSaveItem.setIcon(IconFontSwing.buildIcon(FontAwesome.FLOPPY_O, 20));
        btnSaveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveItemActionPerformed(evt);
            }
        });

        btnDeleteItem.setText("Delete");
        btnDeleteItem.setIcon(IconFontSwing.buildIcon(FontAwesome.TRASH_O, 20));
        btnDeleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteItemActionPerformed(evt);
            }
        });

        btnQuitProgram2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        btnQuitProgram2.setText("Quit Program");
        btnQuitProgram1.setIcon(IconFontSwing.buildIcon(FontAwesome.ARROW_RIGHT, 30));
        btnQuitProgram2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitProgram2ActionPerformed(evt);
            }
        });
        btnQuitProgram2.setIcon(IconFontSwing.buildIcon(FontAwesome.ARROW_RIGHT, 20));

        btnExportCSVItems.setText("Export to CSV Format");
        btnExportToCSVSuppliers.setIcon(IconFontSwing.buildIcon(FontAwesome.DOWNLOAD, 20));
        btnExportCSVItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportCSVItemsActionPerformed(evt);
            }
        });

        btnSortDSCItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortDSCItemsActionPerformed(evt);
            }
        });
        btnSortDSCItems.setIcon(IconFontSwing.buildIcon(FontAwesome.ARROW_DOWN, 20));

        lblSearchItems.setIcon(IconFontSwing.buildIcon(FontAwesome.SEARCH, 20));

        cbSortByItems.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sort By Code", "Sort By Name", "Sort By Supplier", "Sort By Unit", "Sort By Price", "Sort By Supplying" }));

        btnSortASCItems.setIcon(IconFontSwing.buildIcon(FontAwesome.ARROW_UP, 20));
        btnSortASCItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortASCItemsActionPerformed(evt);
            }
        });

        txtSearchItems.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchItemsKeyPressed(evt);
            }
        });

        lblItemIsSupplying.setText("Supplying:");

        tblItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Supplier", "Unit", "Price", "Supply"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblItemsMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblItems);
        if (tblItems.getColumnModel().getColumnCount() > 0) {
            tblItems.getColumnModel().getColumn(0).setResizable(false);
            tblItems.getColumnModel().getColumn(1).setResizable(false);
            tblItems.getColumnModel().getColumn(2).setResizable(false);
            tblItems.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout pnlItemsLayout = new javax.swing.GroupLayout(pnlItems);
        pnlItems.setLayout(pnlItemsLayout);
        pnlItemsLayout.setHorizontalGroup(
            pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemsLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlItemsLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlItemsLayout.createSequentialGroup()
                                .addGap(26, 207, Short.MAX_VALUE)
                                .addComponent(btnQuitProgram2))
                            .addGroup(pnlItemsLayout.createSequentialGroup()
                                .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlItemsLayout.createSequentialGroup()
                                            .addComponent(lblItemCode)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(100, 100, 100))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlItemsLayout.createSequentialGroup()
                                            .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblPrice)
                                                .addComponent(lblSuppliers)
                                                .addComponent(lblItemName)
                                                .addComponent(lblUnit))
                                            .addGap(8, 8, 8)
                                            .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtItemName)
                                                .addComponent(cbSuppliers, 0, 250, Short.MAX_VALUE)
                                                .addComponent(txtItemUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtItemPrice))))
                                    .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlItemsLayout.createSequentialGroup()
                                            .addComponent(btnSortASCItems, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnSortDSCItems)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbSortByItems, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(pnlItemsLayout.createSequentialGroup()
                                            .addComponent(lblSearchItems, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtSearchItems, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlItemsLayout.createSequentialGroup()
                                            .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlItemsLayout.createSequentialGroup()
                                                    .addComponent(lblItemIsSupplying)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(chkItemSupplying)
                                                    .addGap(0, 27, Short.MAX_VALUE))
                                                .addComponent(btnAddNewItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnSaveItem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnDeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(pnlItemsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExportCSVItems, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
        );
        pnlItemsLayout.setVerticalGroup(
            pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemsLayout.createSequentialGroup()
                .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlItemsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblItemCode))
                        .addGap(18, 18, 18)
                        .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblItemName)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSuppliers)
                            .addComponent(cbSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUnit)
                            .addComponent(txtItemUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPrice)
                            .addComponent(txtItemPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkItemSupplying)
                            .addComponent(lblItemIsSupplying))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddNewItem)
                            .addComponent(btnSaveItem)
                            .addComponent(btnDeleteItem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSortASCItems, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSortByItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSortDSCItems, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSearchItems, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearchItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(btnExportCSVItems)
                        .addGap(12, 12, 12)
                        .addComponent(btnQuitProgram2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        tabbedPane.addTab("Items", pnlItems);

        lblLanguage.setText("Language:");

        cbLanguages.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Vietnamese" }));
        cbLanguages.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbLanguagesItemStateChanged(evt);
            }
        });
        cbLanguages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLanguagesActionPerformed(evt);
            }
        });

        lblMode.setText("Mode:");

        jLabel1.setText("Items Management by BangMaple (SE140937)");

        jLabel2.setText("Any change will take effect immediately.");

        jButton1.setText("Support the author");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnChangeMode.setText("Change to White Mode");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblLanguage)
                                    .addComponent(lblMode))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbLanguages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnChangeMode)))
                            .addComponent(jLabel2))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLanguages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLanguage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChangeMode)
                    .addComponent(lblMode))
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addGap(140, 140, 140))
        );

        tabbedPane.addTab("Preferences", jPanel1);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddNewSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewSupplierActionPerformed
        newSupplier();
    }//GEN-LAST:event_btnAddNewSupplierActionPerformed

    private void btnDeleteSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSupplierActionPerformed
        if (!this.txtCode.isEditable() && askBeforeDeleting()) {
            deleteSupplier();
            newSupplier();
        }
    }//GEN-LAST:event_btnDeleteSupplierActionPerformed

    private void btnSaveSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSupplierActionPerformed
        saveSupplier();
        loadSuppliers();
    }//GEN-LAST:event_btnSaveSupplierActionPerformed

    private void btnQuitProgram1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitProgram1ActionPerformed
        // TODO add your handling code here:
        confirmQuitProgram();
    }//GEN-LAST:event_btnQuitProgram1ActionPerformed

    private void txtSearchSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchSupplierActionPerformed

    private void btnSortASCSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortASCSupplierActionPerformed
        sortSuppliersAscending();
    }//GEN-LAST:event_btnSortASCSupplierActionPerformed

    private void btnSortDSCSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortDSCSupplierActionPerformed
        sortSuppliersDescending();
    }//GEN-LAST:event_btnSortDSCSupplierActionPerformed

    private void btnExportToCSVSuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportToCSVSuppliersActionPerformed
        exportSuppliersDataToCSV();
    }//GEN-LAST:event_btnExportToCSVSuppliersActionPerformed

    private void txtSearchSupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSupplierKeyPressed
        initSuppliersFullTextSearch(evt);
    }//GEN-LAST:event_txtSearchSupplierKeyPressed

    private void btnQuitProgram2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitProgram2ActionPerformed
        confirmQuitProgram();
    }//GEN-LAST:event_btnQuitProgram2ActionPerformed

    private void btnSortASCItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortASCItemsActionPerformed
        sortItemsASC();
    }//GEN-LAST:event_btnSortASCItemsActionPerformed

    private void btnSortDSCItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortDSCItemsActionPerformed
        sortItemsDSC();
    }//GEN-LAST:event_btnSortDSCItemsActionPerformed

    private void btnExportCSVItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportCSVItemsActionPerformed
        exportItemsDataToCSV();
    }//GEN-LAST:event_btnExportCSVItemsActionPerformed

    private void btnSaveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveItemActionPerformed
        saveItem();
    }//GEN-LAST:event_btnSaveItemActionPerformed

    private void btnAddNewItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewItemActionPerformed
        newItem();
    }//GEN-LAST:event_btnAddNewItemActionPerformed

    private void btnDeleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteItemActionPerformed
        if (!this.txtItemCode.isEditable() && askBeforeDeleting()) {
            removeItem();
            newItem();
        }
    }//GEN-LAST:event_btnDeleteItemActionPerformed

    private void tblSuppliersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuppliersMousePressed
        initLoadSupplierRowToFields();
    }//GEN-LAST:event_tblSuppliersMousePressed

    private void tblItemsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemsMousePressed
        initLoadItemsRowDataToFields();
    }//GEN-LAST:event_tblItemsMousePressed

    private void txtSearchItemsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchItemsKeyPressed
        initItemsFullTextSearch(evt);
    }//GEN-LAST:event_txtSearchItemsKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(this, "Thanks for supporting me!");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbLanguagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLanguagesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbLanguagesActionPerformed

    private void cbLanguagesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbLanguagesItemStateChanged
        System.out.println("tes");
    }//GEN-LAST:event_cbLanguagesItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewItem;
    private javax.swing.JButton btnAddNewSupplier;
    private javax.swing.JButton btnChangeMode;
    private javax.swing.JButton btnDeleteItem;
    private javax.swing.JButton btnDeleteSupplier;
    private javax.swing.JButton btnExportCSVItems;
    private javax.swing.JButton btnExportToCSVSuppliers;
    private javax.swing.JButton btnQuitProgram1;
    private javax.swing.JButton btnQuitProgram2;
    private javax.swing.JButton btnSaveItem;
    private javax.swing.JButton btnSaveSupplier;
    private javax.swing.JButton btnSortASCItems;
    private javax.swing.JButton btnSortASCSupplier;
    private javax.swing.JButton btnSortDSCItems;
    private javax.swing.JButton btnSortDSCSupplier;
    private javax.swing.JComboBox<String> cbLanguages;
    private javax.swing.JComboBox<String> cbSortByItems;
    private javax.swing.JComboBox<String> cbSortBySupplier;
    private javax.swing.JComboBox<String> cbSuppliers;
    private javax.swing.JCheckBox chkColloborating;
    private javax.swing.JCheckBox chkItemSupplying;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblCode;
    private javax.swing.JLabel lblColloborating;
    private javax.swing.JLabel lblItemCode;
    private javax.swing.JLabel lblItemIsSupplying;
    private javax.swing.JLabel lblItemName;
    private javax.swing.JLabel lblLanguage;
    private javax.swing.JLabel lblMode;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JButton lblSearchItems;
    private javax.swing.JButton lblSearchSupplier;
    private javax.swing.JLabel lblSuppliers;
    private javax.swing.JLabel lblUnit;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel pnlItems;
    private javax.swing.JPanel pnlSuppliers;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tblItems;
    private javax.swing.JTable tblSuppliers;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtItemCode;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtItemPrice;
    private javax.swing.JTextField txtItemUnit;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearchItems;
    private javax.swing.JTextField txtSearchSupplier;
    // End of variables declaration//GEN-END:variables
}
