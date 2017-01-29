import objectdraw.*;
import java.awt.Color;
/**
 * The Folder class defines the blueprint for a Folder object by creating all of the various
 * components that make up a folder (folder, tab, name, color filling within). The class also
 * defines contains, move and remove from canvas methods for a Folder object that enable it to be
 * grabbed, dragged and deleted.
 *
 * @author Sabirah Shuaybi
 * @version 12/10/16
 */
public class Folder
{
    //All the components that constitute a folder object
    private FramedRect folder;
    private FramedRect folderTab;
    private FilledRect folderFill;
    private FilledRect tabFill;
    private Text numText;
    private int folderNum;

    //Constants for the 3 parts of the folder (folder, folder tab, and number)
    private static final int FOLDER_WIDTH = 80;
    private static final int FOLDER_HEIGHT = 55;
    private static final int TAB_WIDTH = 40;
    private static final int TAB_HEIGHT = 10;
    private static final int START_LOC_X = 40;
    private static final int START_LOC_Y = 100;
    private static final int TEXT_BUFFER = 15;

    //Constants for filling in the FramedRect components of a folder (for color)
    public static final double FILL_BUFFER = 1.48;
    public static final double FOLDER_WIDTH_ADJUSTED = FOLDER_WIDTH - 1.3;
    public static final double FOLDER_HEIGHT_ADJUSTED = FOLDER_HEIGHT - 1.3;
    public static final double TAB_WIDTH_ADJUSTED = TAB_WIDTH - 1.3;
    public static final double TAB_HEIGHT_ADJUSTED = TAB_HEIGHT - 1.3;

    public Folder(DrawingCanvas canvas, int folderNum) {

        //Create the main square of folder at a set position on canvas
        folder = new FramedRect(START_LOC_X, START_LOC_Y, FOLDER_WIDTH, FOLDER_HEIGHT, canvas);

        double tabX = folder.getX();
        double tabY = folder.getY() - TAB_HEIGHT;

        //Create the tab part of the folder to the top left of the main folder component
        folderTab = new FramedRect(tabX, tabY, TAB_WIDTH, TAB_HEIGHT, canvas);

        //Define a color for the folder (sky blue)
        Color skyBlue = new Color(135, 206, 235);

        double folderFillX = START_LOC_X + FILL_BUFFER;
        double folderFillY = START_LOC_Y + FILL_BUFFER;

        //Create inner FilledRects within the FramedRects
            //to provide color to the folder while still maintaining the black border

        folderFill = new FilledRect
        (folderFillX, folderFillY, FOLDER_WIDTH_ADJUSTED, FOLDER_HEIGHT_ADJUSTED, canvas);
        folderFill.setColor(skyBlue);

        double tabFillX = tabX + FILL_BUFFER;
        double tabFillY = tabY + FILL_BUFFER;

        tabFill = new FilledRect
        (tabFillX, tabFillY, TAB_WIDTH_ADJUSTED, TAB_HEIGHT_ADJUSTED, canvas);
        tabFill.setColor(skyBlue);

        //Create a text object that will hold the folder's number

        this.folderNum = folderNum;

        numText = new Text("" + folderNum, START_LOC_X, START_LOC_Y, canvas);
        numText.setBold(true);
        numText.setFontSize(20);

        //Center the folder number below the folder
        double numTextCenterX = START_LOC_X + (FOLDER_WIDTH/2) - (numText.getWidth()/2);
        double numTextCenterY = START_LOC_Y + FOLDER_HEIGHT + TEXT_BUFFER;

        numText.moveTo(numTextCenterX, numTextCenterY);
    }

    /* Determines if mouse click is contained within the folder (but not the number) */
    public boolean contains(Location currentPoint) {

        //Main folder and folder tab constitute the contains (not the folder number)
        return ((folder.contains(currentPoint)) || (folderTab.contains(currentPoint)));
    }

    /* Moves all entire folder by individually moving all distinct parts of folder */
    public void move(double dx, double dy) {

        folder.move(dx, dy);
        folderTab.move(dx, dy);
        folderFill.move(dx, dy);
        tabFill.move(dx, dy);
        numText.move(dx, dy);
    }

    public int getNumber() {

      return folderNum;
    }
    /* Removes entire folder from canvas by individually removing all distinct parts */
    public void removeFromCanvas() {

        folder.removeFromCanvas();
        folderTab.removeFromCanvas();
        folderFill.removeFromCanvas();
        tabFill.removeFromCanvas();
        numText.removeFromCanvas();
    }
}
