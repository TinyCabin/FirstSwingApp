import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ShowroomGUI {
    private JFrame frame;
    private JTable showroomTable;
    private JTable vehicleTable;
    private ShowroomTableModel showroomTableModel;
    private VehicleTableModel vehicleTableModel;
    private CarShowroomContainer container;

    public ShowroomGUI(CarShowroomContainer container) {
        this.container = container;
        frame = new JFrame("Car Showroom Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        showroomTableModel = new ShowroomTableModel();
        showroomTable = new JTable(showroomTableModel);


        vehicleTable = new JTable(vehicleTableModel);

        JButton addShowroomButton = new JButton("Add CarShowroom Center");
        addShowroomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter Showroom Name");
                int capacity = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Showroom Capacity"));
                container.addCenter(name, capacity);
                showroomTableModel.fireTableDataChanged();
            }
        });

        showroomTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double-click detected
                    int selectedRow = showroomTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        String name = (String) showroomTableModel.getValueAt(selectedRow, 0);
                        CarShowroom showroom = container.getShowroom(name);

                        // Otwórz nowe okno z listą samochodów dla wybranego salonu
                        JFrame showroomFrame = new JFrame("Car Showroom: " + name);
                        showroomFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        showroomFrame.setSize(800, 500);

                        VehicleTableModel vehicleTableModel = new VehicleTableModel(showroom);
                        JTable vehicleTable = new JTable(vehicleTableModel);

                        JButton addVehicleButton = new JButton("Add Vehicle");
                        addVehicleButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String make = JOptionPane.showInputDialog(showroomFrame, "Enter Vehicle Make");
                                String model = JOptionPane.showInputDialog(showroomFrame, "Enter Vehicle Model");
                                ItemCondition condition = (ItemCondition) JOptionPane.showInputDialog(showroomFrame, "Enter Vehicle Condition", "Vehicle Condition", JOptionPane.QUESTION_MESSAGE, null, ItemCondition.values(), ItemCondition.NEW);
                                double price = Double.parseDouble(JOptionPane.showInputDialog(showroomFrame, "Enter Vehicle Price"));
                                int productionYear = Integer.parseInt(JOptionPane.showInputDialog(showroomFrame, "Enter Vehicle Production Year"));
                                double mileage = Double.parseDouble(JOptionPane.showInputDialog(showroomFrame, "Enter Vehicle Mileage"));
                                double engineCapacity = Double.parseDouble(JOptionPane.showInputDialog(showroomFrame, "Enter Vehicle Engine Capacity"));

                                Vehicle vehicle = new Vehicle(make, model, condition, price, productionYear, mileage, engineCapacity);
                                showroom.addProduct(vehicle);
                                vehicleTableModel.fireTableDataChanged();
                            }
                        });

                        JButton removeVehicleButton = new JButton("Remove Vehicle");
                        removeVehicleButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int selectedRow = vehicleTable.getSelectedRow();
                                if (selectedRow >= 0) {
                                    String make = (String) vehicleTableModel.getValueAt(selectedRow, 0);
                                    String model = (String) vehicleTableModel.getValueAt(selectedRow, 1);
                                    showroom.removeProduct(make, model);
                                    vehicleTableModel.fireTableDataChanged();
                                }
                            }
                        });

                        JPanel buttonPanel = new JPanel(new FlowLayout());
                        buttonPanel.add(addVehicleButton);
                        buttonPanel.add(removeVehicleButton);

                        showroomFrame.add(new JScrollPane(vehicleTable), BorderLayout.CENTER);
                        showroomFrame.add(buttonPanel, BorderLayout.SOUTH);
                        showroomFrame.setVisible(true);
                    }
                }
            }
        });


        JButton removeShowroomButton = new JButton("Remove CarShowroom Center");
        removeShowroomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = showroomTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String name = (String) showroomTableModel.getValueAt(selectedRow, 0);
                    container.removeCenter(name);
                    showroomTableModel.fireTableDataChanged();
                }
            }
        });

        JButton addVehicleButton = new JButton("Add New Vehicle");
        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = showroomTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String name = (String) showroomTableModel.getValueAt(selectedRow, 0);
                    CarShowroom showroom = container.getShowroom(name);
                    String marka = JOptionPane.showInputDialog(frame, "Enter Vehicle Marka");
                    String model = JOptionPane.showInputDialog(frame, "Enter Vehicle Model");
                    ItemCondition condition = (ItemCondition) JOptionPane.showInputDialog(frame, "Enter Vehicle Condition", "Vehicle Condition", JOptionPane.QUESTION_MESSAGE, null, ItemCondition.values(), ItemCondition.NEW);
                    double price = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter Vehicle Price"));
                    int productionYear = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Vehicle Production Year"));
                    double mileage = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter Vehicle Mileage"));
                    double engineCapacity = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter Vehicle Engine Capacity"));

                    Vehicle vehicle = new Vehicle(marka, model, condition, price, productionYear, mileage, engineCapacity);
                    showroom.addProduct(vehicle);
                    vehicleTableModel.fireTableDataChanged();
                }
            }

        });


        JButton removeVehicleButton = new JButton("Remove Vehicle");
        removeVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedShowroomRow = showroomTable.getSelectedRow();
                int selectedVehicleRow = vehicleTable.getSelectedRow();
                if (selectedShowroomRow >= 0 && selectedVehicleRow >= 0) {
                    String showroomName = (String) showroomTableModel.getValueAt(selectedShowroomRow, 0);
                    CarShowroom showroom = container.getShowroom(showroomName);
                    String marka = (String) vehicleTableModel.getValueAt(selectedVehicleRow, 0);
                    String model = (String) vehicleTableModel.getValueAt(selectedVehicleRow, 1);
                    showroom.removeProduct(marka, model);
                    vehicleTableModel.fireTableDataChanged();
                }
            }
        });

        JButton sortCentersButton = new JButton("Sort Centers by Current Load");
        sortCentersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.sortCentersByCurrentLoad();
                showroomTableModel.fireTableDataChanged();
            }
        });


        JPanel showroomPanel = new JPanel(new BorderLayout());
        showroomPanel.add(addShowroomButton, BorderLayout.NORTH);
        showroomPanel.add(new JScrollPane(showroomTable), BorderLayout.CENTER);
        showroomPanel.add(removeShowroomButton, BorderLayout.SOUTH);

        JPanel vehiclePanel = new JPanel(new BorderLayout());
        vehiclePanel.add(addVehicleButton, BorderLayout.NORTH);
        vehiclePanel.add(new JScrollPane(vehicleTable), BorderLayout.CENTER);
        vehiclePanel.add(removeVehicleButton, BorderLayout.SOUTH);


//        frame.add(showroomPanel);
//        frame.setVisible(true);
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.add(showroomPanel);
        //mainPanel.add(vehiclePanel);

        frame.add(mainPanel);
        frame.add(sortCentersButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    class ShowroomTableModel extends AbstractTableModel {
        private String[] columnNames = {"Showroom Name", "Capacity", "Current Load"};

        @Override
        public int getRowCount() {
            return container.getSalony().size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            List<CarShowroom> showrooms = new ArrayList<>(container.getSalony().values());
            return switch (columnIndex) {
                case 0 -> showrooms.get(rowIndex).getNazwaSalonu();
                case 1 -> showrooms.get(rowIndex).getMaksymalnaPojemnoscSalonu();
                case 2 -> showrooms.get(rowIndex).getListaSamochodow().size();
                default -> null;
            };
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }

    class VehicleTableModel extends AbstractTableModel {
        private String[] columnNames = {"Marka", "Model", "Rok produkcji", "Cena", "Przebieg", "Pojemność silnika"};
        private CarShowroom showroom;

        public VehicleTableModel(CarShowroom showroom) {
            this.showroom = showroom;
        }

        @Override
        public int getRowCount() {
            return showroom != null ? showroom.getListaSamochodow().size() : 0;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (showroom != null && !showroom.getListaSamochodow().isEmpty()) {
                List<Vehicle> vehicles = showroom.getListaSamochodow();
                Vehicle vehicle = vehicles.get(rowIndex);
                return switch (columnIndex) {
                    case 0 -> vehicle.getMarka();
                    case 1 -> vehicle.getModel();
                    case 2 -> vehicle.getRokProdukcji();
                    case 3 -> vehicle.getCena();
                    case 4 -> vehicle.getPrzebieg();
                    case 5 -> vehicle.getPojemnoscSilnika();
                    default -> null;
                };
            }
            return null;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public void fireTableDataChanged() {
            super.fireTableDataChanged();
        }
    }
}