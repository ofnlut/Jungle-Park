//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P02 JUNGLE PARK 1000
// Files: JunglePArk.java
// Course: CS300, Fall, 2018
//
// Author: Devin Mozee
// Email: mozee@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Random;

public class JunglePark {

  private static PApplet processing; // PApplet object that represents the graphic
  // interface of the JunglePark application
  private static PImage backgroundImage; // PImage object that represents the
  // background image
  private static Tiger[] tigers; // array storing the current tigers present
  // in the Jungle Park

  private static Random randGen; // Generator of random numbers

  /*
   * Adds or removes a tiger based on user input tiger is removed based off the first index
   * 
   */
  public static void keyPressed() {

    // Add tiger when 't' or 'T' is pressed
    if (processing.key == 't' || processing.key == 'T') {
      for (int i = 0; i < tigers.length; i++) {
        if (tigers[i] == null) {
          tigers[i] = new Tiger(processing, (float) randGen.nextInt(processing.width),
              (float) randGen.nextInt(processing.height));
          break;
        }
      }
    }

    // Remove tiger when 'r' or 'R' is pressed
    if (processing.key == 'r' || processing.key == 'R') {
      for (int i = 0; i < tigers.length; i++) {
        if (!(tigers[i] == null)) {
          if (isMouseOver(tigers[i])) {
            tigers[i] = null;
            break;
          }
        }
      }
    }
  }

  // Check if mouse left click is down
  public static void mouseDown() {

    // If tiger a tiger exists follow the mouse while mouse left click is down
    for (int tiger = 0; tiger < tigers.length; tiger++) {
      if (!(tigers[tiger] == null)) {
        if (isMouseOver(tigers[tiger])) {
          tigers[tiger].setDragging(true);
          break;
        }
      }
    }

  }

  // Check if mouse left click is up
  public static void mouseUp() {

    // For every tiger that exist if mouse left click is up do not follow mouse
    for (int tiger = 0; tiger < tigers.length; tiger++) {
      if (!(tigers[tiger] == null)) {
        if (isMouseOver(tigers[tiger])) {
          tigers[tiger].setDragging(false);
        }
      }
    }
  }

  /*
   * Returns a boolean if mouse is over a tiger
   * 
   * @param tiger array parameter
   */
  public static boolean isMouseOver(Tiger tiger) {


    // Declare helper variables
    float positiveX = (tiger.getPositionX() + (tiger.getImage().width / 2)); // Determine right of X
                                                                             // center
    float negativeX = (tiger.getPositionX() - (tiger.getImage().width / 2)); // Determine left of X
                                                                             // center

    float positiveY = (tiger.getPositionY() + (tiger.getImage().height / 2));
    float negativeY = (tiger.getPositionY() - (tiger.getImage().height / 2));

    // Compares mouse position to determine if mouse x/y is within the tiger image width and height
    if ((processing.mouseX >= negativeX && processing.mouseX <= positiveX)
        && (processing.mouseY <= positiveY && processing.mouseY >= negativeY)) {
      return true;
    } else {
      return false;
    }
  }

  /*
   * 
   * Updates properties throughout the duration of the application
   * 
   */
  public static void update() {


    // Redraw background to prevent trailing.
    processing.background(245, 255, 250);
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);


    // Update tiger positions
    for (int j = 0; j < tigers.length; j++) {
      if (!(tigers[j] == null)) {
        tigers[j].draw();
      }
    }


  }

  /**
   * Defines the initial environment properties of the application
   * 
   * @param processingObj represents a reference to the graphical interface of the application
   */
  public static void setup(PApplet processingObj) {


    processing = processingObj; // Initialize the processing field to the one
                                // passed into the input argument parameter

    // Set the color used for the background of the Processing window
    processing.background(245, 255, 250); // Mint cream color

    // initialize and load the image of the background
    backgroundImage = processing.loadImage("images/background.png");


    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);

    // Create a Random object and store its reference in randGen
    randGen = new Random();

    // Note: Any new object declares should happen AFTER this
    tigers = new Tiger[8]; // statement

    // Initialize all tiger arrays to null
    for (int i = 0; i < tigers.length; i++) {
      tigers[i] = null;
    }



  }

  public static void main(String[] args) {

    // Starts application window and update method.
    Utility.startApplication();
  }

}
