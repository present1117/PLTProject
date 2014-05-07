import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	//initialize variables
    private Image boardImage;
    //initialize components
    private JPanel centerPanel = new JPanel();
    //private JPanel southPanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    //initialize arrays to hold panels and images of the board
    private int numOfRows = Board.row();
    private int numOfColumns = Board.col();
    private JLabel[] labels = new JLabel[numOfRows*numOfColumns];
    private ImagePanel[] panels = new ImagePanel[numOfRows*numOfColumns];
    
    public BoardGUI(Image boardImage) {
        this.boardImage = boardImage;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        setTitle("Board Game Designer");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addComponentsToPane(getContentPane());

        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    /**
     * Adds all the necessary components to the content pane of the JFrame, and
     * adds appropriate listeners to components.
     */
    private void addComponentsToPane(Container contentPane) {

        GridLayout gridLayout = new GridLayout(numOfRows, numOfColumns);
        //gridLayout.setHgap(1);
        //gridLayout.setVgap(1);
        centerPanel.setLayout(gridLayout);
        centerPanel.setBackground(Color.black);

        //call method to add labels to north panel
        addLabelsToNorthPanel();
        //call method to add panels to west panel
        addLabelsToWestPanel();
        //call method to add panels and labels to the center panel which holds the board
        addPanelsAndLabels();
        //add all panels to frame
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(westPanel, BorderLayout.WEST);
    }

    private void addLabelsToNorthPanel(){
    	GridLayout gridLayout = new GridLayout(0, numOfColumns);
    	northPanel.setLayout(gridLayout);
    	JLabel[] lbls = new JLabel[numOfColumns];
    	for (int i = 0; i < numOfColumns; i++) {
            lbls[i] = new JLabel(i + "");
            northPanel.add(lbls[i]);
        }
    }

    private void addLabelsToWestPanel() {
        GridLayout gridLayout = new GridLayout(numOfRows, 0);
        westPanel.setLayout(gridLayout);
        JLabel[] lbls = new JLabel[numOfRows];
        for (int i = 0; i < numOfRows; i++) {
        	lbls[i] = new JLabel(i + "");
            westPanel.add(lbls[i]);
        }
    }

    private void addPanelsAndLabels() {
        //call method to create panels with background images and appropriate names
        addPanelsAndImages();

        for (int i = 0; i < panels.length; i++) {
            labels[i] = new JLabel();

            //used to know the position of the label on the board
            labels[i].setName(panels[i].getName());

            panels[i].add(labels[i]);

            //adds panels created in addPanelsAndImages()
            centerPanel.add(panels[i]);
        }
    }

    //this method will create panels with background images of chess board and set its name according to 1-8 for rows and A-H for coloumns
    private void addPanelsAndImages() {
        int count = 0;

        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfColumns; col++) {            
                panels[count] = new ImagePanel(boardImage);
                panels[count].setBorder(BorderFactory.createLineBorder(Color.black));
                panels[count].setName(row + "" + col);
                count++;
            }
        }
    }

    //method sets image of a label at a certain position in the board according to the block name i.e D4
    public void addPiece(ImageIcon img, String position) {
        for (int s = 0; s < labels.length; s++) {
            if (labels[s].getName().equalsIgnoreCase(position)) {
            	Image scaledImage = img.getImage().getScaledInstance((int)(panels[s].getWidth()/1.2), (int)(panels[s].getHeight()/1.2), Image.SCALE_SMOOTH);
            	img.setImage(scaledImage);
                labels[s].setIcon(img);
            }
        }
    }

    //nested class used to set the background of frame contenPane
    class ImagePanel extends JPanel {
    	private static final long serialVersionUID = 1L;
		private Image image;

        //Default constructor used to set the image for the background for the
        //instance
        public ImagePanel(Image img) {
            image = img;
        }

        @Override
        protected void paintComponent(Graphics g) {
            //draws image to background to scale of frame
            g.drawImage(image, 0, 0, null);
            
        }
    }
}
