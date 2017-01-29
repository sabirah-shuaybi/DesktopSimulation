import objectdraw.*;
/**
 * The FolderCollection class manages the entire collection of ten folders.
 * In order to manage the collection, it has behaviors (methods) that allow it
 * to add a folder to collection (if there's space), remove a folder from collection,
 * return the folder that was grabbed by the user and return the index of a folder.
 *
 * @author Sabirah Shuaybi
 * @version 12/10/16
 */
public class FolderCollection
{
    //Maximum number of folders program will keep track of
    private static final int MAX_FOLDERS = 10;

    //Array to hold all folders
    private Folder[] folderArray;

    //Visial number naming system of folder initialized to 1
    private int folderNumber = 1;

    private DrawingCanvas canvas;

    public FolderCollection(DrawingCanvas canvas) {

        //Saving reference to canvas
        this.canvas = canvas;

        //Create an empty array large enough for 10 folders
        folderArray = new Folder[MAX_FOLDERS];
    }

    /* Adds a folder to the collection */
    public void addAFolder() {

        //FIrst create a folder, then attempt to add it to collection.
            //If collection is full, the new folder will display but won't
                //be part of the collection (user won't be able to drag/delete it)
        Folder newFolder = new Folder(canvas, folderNumber++);

        //Before adding a folder, first check if there's space
        for (int i=0; i<folderArray.length; i++) {
            if (folderArray[i] == null) {
                folderArray[i] = newFolder;
                //Break out of loop as soon as you've filled the first non-null slot
                break;
            }
        }

        System.out.println("After add - " + toString());

    }

    /* Finds a folder at a particular location */
    public Folder getFolderAt(Location point) {

        //Walk through collection of folders to find the one containing the point
        for (int i=0; i<folderArray.length; i++) {
            if (folderArray[i] != null && folderArray[i].contains(point)) {

                //Return the folder found
                return folderArray[i];
            }
        }
        //Or, if no folder contains point, return null
        return null;
    }

    /* Returns the index value of whichever folder element is passed in */
    private int findIndex(Folder folder) {

        for(int i=0; i<folderArray.length; i++) {

            //If folder at given index matches the folder passed in, return index
            if (folderArray[i] == folder)
                return i;
        }
        //Should never reach this point;
        return -1;
    }

    /* Removes a particular folder from collection and rearranges/compacts array
    so that there are no gaps in the array. All empty spaces (if any) are at the end */
    public void removeFolder(Folder folder) {

        //Get the index value of the folder
        int index = findIndex(folder);

        System.out.println("Before compact - " + toString());

        //Physically remove folder from canvas
        folderArray[index].removeFromCanvas();

        //Deleting last element is easy - just set it to null. No compacting
            //of the array is required.
        if (index == folderArray.length-1) {
            folderArray[index] = null;
            return;
        }

        //If a folder that is not the last element of array is deleted,
            //then compact the array
        for (int i=index; i<folderArray.length-1; i++) {

            //If the next element in array is empty, exit loop
                //Since there is nothing more to compact
            if (folderArray[i+1] == null) {
                folderArray[i] = null;
                break;
            }
            //Transfer the folder reference of the next slot in array into the current slot
            folderArray[i] = folderArray[i+1];
            folderArray[i+1] = null;
        }

        System.out.println("After compact - " + toString());
    }

    //Return a string representation of the collection
    public String toString() {

        StringBuffer buffer = new StringBuffer("FolderCollection: ");
        buffer.append("[ ");

        for (int i=0; i<folderArray.length; i++) {
            if (folderArray[i] != null)
                buffer.append(folderArray[i].getNumber());
            else
                buffer.append("-");

            if (i<folderArray.length-1)
                buffer.append(" | ");
        }

        buffer.append(" ]");
        return buffer.toString();
    }

}
