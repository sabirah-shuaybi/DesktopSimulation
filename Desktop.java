import objectdraw.*;
/**
 * This program defines a few of the features available on a typical desktop.
 * In particular, the program allows for the creation of folders along with moving
 * and deleting them by dragging to the trash.
 *
 * The Desktop class creates the intitial display with the trash can image and an instance of
 * Button with a New Folder functionality. In addition, this class handles all the mouse events
 * required for clicking the New Folder button and dragging the Folders across the canvas.
 *
 * NOTE: If the folder collection becomes full, more folders CAN be created.
 * It's just that the user won’t be able to drag them. If the user drags a folder
 * to the trash, that will free up room so another NEW folder can be created and dragged.
 *
 * @author Sabirah Shuaybi
 * @version 12/10/16
 */
public class Desktop extends WindowController
{
    //Size of canvas
    private static final int WIN_SIZE = 600;

    //Size of trash can image
    private static final int PIC_LOC_X = 440;
    private static final int PIC_LOC_Y = 380;

    private Picture picture;
    private VisibleImage visibleImage;

    private FolderCollection folderCollection;
    private Button newFolder;

    //To hold the folder that is grabbed by user
    private Folder folderGrabbed;

    //To hold the location of the point where mouse was last seen, for dragging purposes
    private Location lastPoint;

    public void begin() {

        //Resize canvas to accomodate space for all potential folders
        resize(WIN_SIZE, WIN_SIZE);

        //Load and display trash can pic
        loadPicture();
        displayPicture();

        //Create a button for New Folder
        newFolder = new Button(canvas, "New Folder");

        //Create an temporarily empty collection of folders
        folderCollection = new FolderCollection(canvas);

    }

    /* Loads picture url and stores it in instance variable picture */
    private void loadPicture() {

        picture = new Picture ("trashempty.png");
    }

    /* Displays loaded picture onto canvas */
    private void displayPicture() {

        //Create a visible image at given coordinate on canvas
        visibleImage = picture.createVisibleImage(PIC_LOC_X, PIC_LOC_Y, canvas);
    }

    public void onMouseClick(Location point) {

        //If the New Folder button is clicked...
        if(newFolder.contains(point)) {

            //Create/display a new folder and add it to collection if there's space
            folderCollection.addAFolder();
        }

        System.out.println(folderCollection.toString());
    }

    public void onMousePress(Location currentPoint) {

        //Ask folder collection for the folder that was grabbed
        folderGrabbed = folderCollection.getFolderAt(currentPoint);

        //If an actual folder was grabbed,
        if (folderGrabbed != null) {

            //Capture last location of mouse/folder (for onMouseDrag method)
            lastPoint = currentPoint;
        }

    }

    public void onMouseDrag(Location currentPoint) {

        //If an actual folder was grabbed,
        if(folderGrabbed != null) {

            double dx = currentPoint.getX() - lastPoint.getX();
            double dy = currentPoint.getY() - lastPoint.getY();

            //Move grabbed folder the distance from last point to the current point
            folderGrabbed.move(dx, dy);

            //Document the last location of mouse
            lastPoint = currentPoint;
        }
    }

    public void onMouseRelease(Location currentPoint) {

        //If a folder has been grabbed AND is released anywhere within trash can pic,
        if (folderGrabbed != null && visibleImage.contains(currentPoint))

            //Delete the folder (from collection and from canvas)
            folderCollection.removeFolder(folderGrabbed);

        //Prevent movement of the folder that was grabbed after it's been released
        folderGrabbed = null;
    }
}
