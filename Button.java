import java.awt.*;
import objectdraw.*;

/**
 * The button class is responsible for constructing a button object, perfectly centering
 * the text within button, and finally defining a contains method for the button object.
 *
 * @author Sabirah Shuaybi
 * @version 12/10/16
 */

public class Button
{
    //Two components that make up a button: rectangle and text
    private FramedRect button;
    private Text description;

    private static final int BUTTON_LOC = 40;
    private static final int BUTTON_HEIGHT = 25;

    /* Private method that calculates width of a button based on length of description (string).
    The length of the string is then multiplied by a constant
    for neat spacing of text within the button */
    private double computeWidth(String buttonText) {

        return buttonText.length() * 8;
    }

    /* Constructor for Button class */
    public Button (DrawingCanvas canvas, String buttonText) {

        //Determine button width based on length of text passed in
        double buttonWidth = computeWidth(buttonText);

        button = new FramedRect(BUTTON_LOC, BUTTON_LOC, buttonWidth, BUTTON_HEIGHT, canvas);
        description = new Text(buttonText, BUTTON_LOC, BUTTON_LOC, canvas);

        //Calculate center of button
        double buttonCenterX = BUTTON_LOC + (buttonWidth/2);
        double buttonCenterY = BUTTON_LOC + (BUTTON_HEIGHT/2);

        //Configure the centering of text within button
        double descriptionCenteredX = buttonCenterX - (description.getWidth()/2);
        double descriptionCenteredY = buttonCenterY - (description.getHeight()/2);

        //Move text to centered location
        description.moveTo(descriptionCenteredX, descriptionCenteredY);

    }

    /* Determines whether click was contained within button */
    public boolean contains (Location mouseClick) {

        return (button.contains(mouseClick));
    }
}
