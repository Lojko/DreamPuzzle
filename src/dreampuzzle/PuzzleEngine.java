package dreampuzzle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JButton;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * The puzzle engine handles everything to do with constructing and playing a puzzle. Different GUI's
 * can initialize an object of PuzzleEngine and still use all of the provided functionality.
 * It constructs and draws beams as well as provides public functions to setup a puzzle.
 * @author Michael
 */
public class PuzzleEngine
{
    //Engine member variables
    private int m_GridWidth, m_GridHeight, m_PanelWidth, m_PanelHeight, m_CurrentSelectedButton;
    private JButton[] m_ButtonArray;
    private Totem[] m_TotemArray;
    private Color m_DefaultColour;
    private ArrayList<Line2D> m_LineArrayList;
    private boolean m_boolButtonSelected;
    private Totem m_StartingTotem, m_WinningTotem;

    //PuzzleEngine constructor, it requires the array of buttons used in the GUI, the array of totems used by the GUI,
    //the width and heeight of the JPanel or container used for the GUI, the width and height of the button grid and the
    //totem which gauges which totems start and end the puzzle.
    public PuzzleEngine(JButton[] buttonArray, Totem[] totemArray, int panelWidth, int panelHeight, int gridWidth, int gridHeight, Totem startTotem, Totem winnerTotem)
    {
        m_ButtonArray = buttonArray;
        m_TotemArray = totemArray;
        m_GridWidth = gridWidth;
        m_GridHeight = gridHeight;
        m_PanelWidth = panelWidth;
        m_PanelHeight = panelHeight;
        m_StartingTotem = startTotem;
        m_WinningTotem = winnerTotem;
        m_DefaultColour = m_ButtonArray[0].getBackground();
        m_LineArrayList = new ArrayList<Line2D>();
    }

    //Function used to set and reset the grid.
    public void setGrid()
    {
        m_boolButtonSelected = false;
        for(int i = 0; i < m_ButtonArray.length; i++)
        {
            if(m_ButtonArray[i] != m_WinningTotem.getTotemButton() && m_ButtonArray[i] != m_StartingTotem.getTotemButton())
            {
                m_ButtonArray[i].setIcon(null);
            }
        }
        setTotemPositions();
        m_LineArrayList.clear();
    }

    //Public function which the GUI uses to draw the beam, the output of the starting totem and the button
    //that the starting button is set to are the parameter requirements.
    public ArrayList<Line2D> drawBeam()
    {
        //Calculate the x,y coordinates of the button
        m_LineArrayList.clear();
        //Construct the beam using the starting totems output, x,y coordinates and button
        constructBeam(m_StartingTotem.getOutput(),
                calculateCoordinates(searchButtonArray(m_StartingTotem.getTotemButton())),
                m_StartingTotem.getTotemButton());
        //Return the list of lines that the beam is made up of
        return m_LineArrayList;
    }

    //Function which calculates the x,y coordinates of a button using its array reference and the width of the grid
    private int[] calculateCoordinates(int n)
    {
        int x, y;

        //Check if the button is not on the first row of the grid
        if(n > m_GridWidth)
        {
            /*Calculate the x and y coordinates for buttons that are not on the first
             *row of the grid.
             */
            if(n % m_GridWidth == 0)
            {
                x = m_GridWidth;
                y = n / m_GridWidth;
            }
            else
            {
                x = n % m_GridWidth;
                y = ((n - x) / m_GridWidth) + 1;
            }
        }
        //Set the x and y coordinates accordingly for the first row
        else
        {
            y = 1;
            x = n;
        }

        //Return a int array of the x and y coords.
        int[] xy = new int[2];
        xy[0] = x;
        xy[1] = y;
        return xy;
    }

    //Function which searchs the button array for a specific button and returns its index
    private int searchButtonArray(JButton button)
    {
        for(int index = 0; index < m_ButtonArray.length; index++)
        {
            if(m_ButtonArray[index] == button)
            {
                return index + 1;
            }
        }

        return -1;
    }

    //Function which checks the surrounding grid spaces for a totem, returning true if there is one 
    //and false if there is not. This prevents totems being placed next to each other.
    private boolean checkSurrounding(int x, int y)
    {
        for(int a = 1; a >= -1; a-- )
        {
            int calcX = x - a;

            if(calcX >= 1 && calcX <= m_GridWidth)
            {
                for(int b = 1; b >= -1; b--)
                {
                    int calcY = y - b;

                    if(calcY >= 1 && calcY <= m_GridHeight)
                    {
                         if(checkArray(calcX,calcY) == true)
                        {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    //Function checks if the selected gird reference has a totem on it already using it's x,y coordinate 
    //to find the appropriate button
    private boolean checkArray(int x, int y)
    {
        int i = (((y - 1) * 6) + x);

        if(m_ButtonArray[i - 1].getIcon() != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //Function which checks the movement of a totem to ensure it is not moved more than a single grid point
    //in any direction.
    private boolean checkMovement(int selectedButton, int x, int y)
    {
        //x,y coordinates of the selected button
        int[] selectedCoordinates = calculateCoordinates(selectedButton);
        for(int a = 1; a >= -1; a-- )
        {
            for(int b = 1; b >= -1; b--)
            {
                int checkX = x - a;
                int checkY = y - b;
                if(selectedCoordinates[0] == checkX && selectedCoordinates[1] == checkY)
                {
                    return true;
                }
            }
        }

        return false;
    }

    /*Function which detects completion of the puzzle and 'builds' the beam. Using the output of a totem,
    * it's x/y grid coordinates and the starting button of the beam, it recursively draws each line path
    * of the beam  until  its hits a totem, goes outside of the panel or passes through the final totem.*/
    private void constructBeam(int output, int[] coordinates, JButton startingButton)
    {
        //Check for puzzle completion
        if(startingButton == m_WinningTotem.getTotemButton())
        {
            int answer = JOptionPane.showConfirmDialog(null, "Puzzle Completed! Play Again?", "Puzzle Completed!", JOptionPane.YES_NO_OPTION);
            //Check if the player wishes to reset the puzzle
            if(answer == 0)
            {
                setGrid();
            }
            //Close the puzzle
            else
            {
                System.exit(0);
            }
        }
        else
        {
            //Case statement which varies depending on the output direction of a totem
            switch(output)
            {
                case 1:
                    //Loop decrementing the x value to see if any totems are in the beams way
                    for(int i = coordinates[0]; i > 0; i--)
                    {
                        //Check if there is a totem in the way of the beam
                        if(checkArray(i,coordinates[1]) == true)
                        {
                            //Retrieve which button is blocking
                            JButton button = retrieveButton(i,coordinates[1]);

                            //Plot the beams path and add the line to the list.
                            Line2D beamOutputOne = new Line2D.Double(
                                    startingButton.getLocation().x + startingButton.getWidth() / 2,
                                    startingButton.getLocation().y,
                                    button.getLocation().x + button.getWidth() / 2,
                                    button.getLocation().y + button.getHeight());

                            m_LineArrayList.add(beamOutputOne);

                            //Check if the input is the same as the output
                            checkTotemInterceptInput(button, output);
                            return;
                        }
                    }

                    Line2D beamOutputOne = new Line2D.Double(
                    startingButton.getLocation().x + startingButton.getWidth() / 2,
                    startingButton.getLocation().y,
                    startingButton.getLocation().x + startingButton.getWidth() / 2,
                    0);

                    m_LineArrayList.add(beamOutputOne);

                    break;

                case 2:
                    int calcXTwo = coordinates[0];
                    //Loop decrementing the y value to see if any totems are in the beams way
                    for(int i = coordinates[1] - 1; i > 0; i--)
                    {
                        //Prevent the check for the beam intercept if the x value is beyond the grid width
                        if( calcXTwo <= m_GridWidth)
                        {
                            calcXTwo++;
                        }
                        else
                        {
                            break;
                        }

                        //Check if there is a totem in the way of the beam
                        if(checkArray(calcXTwo,i) == true && calcXTwo <= m_GridWidth && i > 0)
                        {
                            //Retrieve which button is blocking
                            JButton button = retrieveButton(calcXTwo,i);

                            //Plot the beams path and add the line to the list.
                            Line2D beamOutputTwo = new Line2D.Double(
                                    startingButton.getLocation().x + startingButton.getWidth(),
                                    startingButton.getLocation().y,
                                    button.getLocation().x,
                                    button.getLocation().y + button.getHeight());

                            m_LineArrayList.add(beamOutputTwo);

                            //Check if the input is the same as the output
                            checkTotemInterceptInput(button, output);
                            return;
                        }
                    }

                    if(m_PanelWidth - (startingButton.getLocation().x + startingButton.getWidth()) <
                        startingButton.getLocation().y)
                    {
                        Line2D beamOutputTwo = new Line2D.Double(
                        startingButton.getLocation().x + startingButton.getWidth(),
                        startingButton.getLocation().y,
                        m_PanelWidth,
                        startingButton.getLocation().y - (m_PanelWidth - (startingButton.getLocation().x + startingButton.getWidth())));
                        m_LineArrayList.add(beamOutputTwo);
                    }
                    else
                    {
                        Line2D beamOutputTwo = new Line2D.Double(
                        startingButton.getLocation().x + startingButton.getWidth(),
                        startingButton.getLocation().y,
                        (startingButton.getLocation().x + startingButton.getWidth()) + startingButton.getLocation().y,
                        0);
                        m_LineArrayList.add(beamOutputTwo);
                    }
                    break;

                case 3:
                    //Loop decrementing the x value to see if any totems are in the beams way
                    for(int i = coordinates[0] + 1; i <= m_GridWidth; i++)
                    {
                        //Check if there is a totem in the way of the beam
                        if(checkArray(i,coordinates[1]) == true)
                        {
                            //Retrieve which button is blocking
                            JButton button = retrieveButton(i,coordinates[1]);

                            //Plot the beams path and add the line to the list.
                            Line2D beamOutputThree = new Line2D.Double(
                                    startingButton.getLocation().x + startingButton.getWidth(),
                                    startingButton.getLocation().y + startingButton.getHeight() / 2,
                                    button.getLocation().x,
                                    button.getLocation().y + button.getHeight() / 2);

                            m_LineArrayList.add(beamOutputThree);

                            //Check if the input is the same as the output
                            checkTotemInterceptInput(button, output);
                            return;
                        }
                    }

                    Line2D beamOutputThree = new Line2D.Double(
                    startingButton.getLocation().x + startingButton.getWidth(),
                    startingButton.getLocation().y + startingButton.getHeight() / 2,
                    m_PanelWidth,
                    startingButton.getLocation().y + startingButton.getHeight() / 2);

                    m_LineArrayList.add(beamOutputThree);

                    break;

                case 4:
                    int calcXFour = coordinates[0];
                    //Loop decrementing the y value to see if any totems are in the beams way
                    for(int i = coordinates[1] + 1; i <= m_GridHeight; i++)
                    {
                        //Prevent the check for the beam intercept if the x value is beyond the grid width
                        if( calcXFour < m_GridWidth)
                        {
                            calcXFour++;
                        }
                        else
                        {
                            break;
                        }

                        //Check if there is a totem in the way of the beam
                        if(checkArray(calcXFour,i) == true && calcXFour <= m_GridWidth && i <= m_GridHeight)
                        {
                            //Retrieve which button is blocking
                            JButton button = retrieveButton(calcXFour,i);

                            //Plot the beams path and add the line to the list.
                            Line2D beamOutputTwo = new Line2D.Double(
                                    startingButton.getLocation().x + startingButton.getWidth(),
                                    startingButton.getLocation().y + startingButton.getHeight(),
                                    button.getLocation().x,
                                    button.getLocation().y);

                            m_LineArrayList.add(beamOutputTwo);

                            //Check if the input is the same as the output
                            checkTotemInterceptInput(button, output);
                            return;
                        }
                    }

                    if(m_PanelWidth - (startingButton.getLocation().x + startingButton.getWidth()) <
                        startingButton.getLocation().y + startingButton.getHeight())
                    {
                        Line2D beamOutputTwo = new Line2D.Double(
                        startingButton.getLocation().x + startingButton.getWidth(),
                        startingButton.getLocation().y + startingButton.getHeight(),
                        m_PanelWidth,
                        startingButton.getLocation().y + startingButton.getHeight() + (m_PanelWidth - (startingButton.getLocation().x + startingButton.getWidth())));
                        m_LineArrayList.add(beamOutputTwo);
                    }
                    else
                    {
                        Line2D beamOutputTwo = new Line2D.Double(
                        startingButton.getLocation().x + startingButton.getWidth(),
                        startingButton.getLocation().y + startingButton.getHeight(),
                        startingButton.getLocation().x + startingButton.getWidth() + (m_PanelHeight -  (startingButton.getLocation().y + startingButton.getWidth())),
                        m_PanelHeight);
                        m_LineArrayList.add(beamOutputTwo);
                    }
                    break;

                case 5:
                    //Loop incrementing the y value to see if any totems are in the beams way
                    for(int i = coordinates[1] + 1; i <= m_GridHeight; i++)
                    {
                        //Check if there is a totem in the way of the beam
                        if(checkArray(coordinates[0],i) == true)
                        {
                            //Retrieve which button is blocking
                            JButton button = retrieveButton(coordinates[0],i);

                            //Plot the beams path and add the line to the list.
                            Line2D beamOutputFive = new Line2D.Double(
                                    startingButton.getLocation().x + startingButton.getWidth() / 2,
                                    startingButton.getLocation().y + startingButton.getHeight(),
                                    button.getLocation().x + button.getWidth() / 2,
                                    button.getLocation().y);

                            m_LineArrayList.add(beamOutputFive);

                            //Check if the input is the same as the output
                            checkTotemInterceptInput(button, output);
                            return;
                        }
                    }

                    Line2D beamOutputFive = new Line2D.Double(
                    startingButton.getLocation().x + startingButton.getWidth() / 2,
                    startingButton.getLocation().y + startingButton.getHeight(),
                    startingButton.getLocation().x + startingButton.getWidth() / 2,
                    m_PanelHeight);

                    m_LineArrayList.add(beamOutputFive);
                    break;

                case 6:
                    //Increment the y value instead of the x value to ensure the x never has a value less than 0
                    int calcYSix = coordinates[1];
                    //Loop decrementing the x value to see if any totems are in the beams way
                    for(int i = coordinates[0] - 1; i > 0; i--)
                    {
                        //Prevent the check for the beam intercept if the y value is beyond the gridHeight
                        if(calcYSix < m_GridHeight)
                        {
                             calcYSix++;
                        }
                        else
                        {
                            break;
                        }

                        //Check if there is a totem in the way of the beam
                        if(checkArray(i, calcYSix) == true && i > 0 && calcYSix <= m_GridHeight)
                        {
                            //Retrieve which button is blocking
                            JButton button = retrieveButton(i, calcYSix);

                            //Plot the beams path and add the line to the list.
                            Line2D beamOutputSix = new Line2D.Double(
                                    startingButton.getLocation().x,
                                    startingButton.getLocation().y + startingButton.getHeight(),
                                    button.getLocation().x + button.getWidth(),
                                    button.getLocation().y);

                            m_LineArrayList.add(beamOutputSix);

                            //Check if the input is the same as the output
                            checkTotemInterceptInput(button, output);
                            return;
                        }
                    }

                    if(startingButton.getLocation().x < m_PanelHeight - (startingButton.getLocation().y + startingButton.getHeight()))
                    {
                        Line2D beamOutputSix = new Line2D.Double(
                        startingButton.getLocation().x,
                        startingButton.getLocation().y + startingButton.getHeight(),
                        0,
                        startingButton.getLocation().y + startingButton.getHeight() + startingButton.getLocation().x);
                        m_LineArrayList.add(beamOutputSix);
                    }
                    else
                    {
                        Line2D beamOutputSix = new Line2D.Double(
                        startingButton.getLocation().x,
                        startingButton.getLocation().y + startingButton.getHeight(),
                        startingButton.getLocation().x - (m_PanelHeight - (startingButton.getLocation().y + startingButton.getHeight())) ,
                        m_PanelHeight);
                        m_LineArrayList.add(beamOutputSix);
                    }
                    break;

                case 7:
                    //Loop decrementing the x value to see if any totems are in the beams way
                    for(int i = coordinates[0] - 1; i > 0; i--)
                    {
                        //Check if there is a totem in the way of the beam
                        if(checkArray(i,coordinates[1]) == true)
                        {
                            //Retrieve which button is blocking
                            JButton button = retrieveButton(i,coordinates[1]);

                            //Plot the beams path and add the line to the list.
                            Line2D beamOutputSeven = new Line2D.Double(
                                    startingButton.getLocation().x,
                                    startingButton.getLocation().y + startingButton.getHeight() / 2,
                                    button.getLocation().x + button.getWidth() ,
                                    button.getLocation().y + button.getHeight() / 2);

                            m_LineArrayList.add(beamOutputSeven);

                            //Check if the input is the same as the output
                            checkTotemInterceptInput(button, output);
                            return;
                        }
                    }

                    Line2D beamOutputSeven = new Line2D.Double(
                    startingButton.getLocation().x,
                    startingButton.getLocation().y + startingButton.getHeight() / 2,
                    0,
                    startingButton.getLocation().y + startingButton.getHeight() / 2);

                    m_LineArrayList.add(beamOutputSeven);

                    break;

                case 8:
                    int calcXEight = coordinates[0];
                    //Loop decrementing the x value to see if any totems are in the beams way
                    for(int i = coordinates[1] - 1; i > 0; i--)
                    {
                        //Prevent the check for the beam intercept if the x value is below the minimum the grid width
                        if(calcXEight > 1)
                        {
                            calcXEight--;
                        }
                        else
                        {
                            break;
                        }

                        //Check if there is a totem in the way of the beam
                        if(checkArray(calcXEight,i) == true && calcXEight > 0 && i > 0)
                        {
                            //Retrieve which button is blocking
                            JButton button = retrieveButton(calcXEight,i);

                            //Plot the beams path and add the line to the list.
                            Line2D beamOutputSix = new Line2D.Double(
                                    startingButton.getLocation().x,
                                    startingButton.getLocation().y,
                                    button.getLocation().x + button.getWidth(),
                                    button.getLocation().y + button.getHeight());

                            m_LineArrayList.add(beamOutputSix);

                            //Check if the input is the same as the output
                            checkTotemInterceptInput(button, output);
                            return;
                        }
                    }

                    if(startingButton.getLocation().x < startingButton.getLocation().y)
                    {
                        Line2D beamOutputSix = new Line2D.Double(
                        startingButton.getLocation().x,
                        startingButton.getLocation().y,
                        0,
                        startingButton.getLocation().y - startingButton.getLocation().x);
                        m_LineArrayList.add(beamOutputSix);
                    }
                    else
                    {
                        Line2D beamOutputSix = new Line2D.Double(
                        startingButton.getLocation().x,
                        startingButton.getLocation().y,
                        startingButton.getLocation().x - startingButton.getLocation().y,
                        0);
                        m_LineArrayList.add(beamOutputSix);
                    }
                    break;

                default:
            }
        }
    }
        
    /*The checkTotemInput function checks the intercepting totems input value and compares it against
     the output value of the previous totem. If they correlate, the beam has to be redirected and recalculated.
     drawBeam() is recursively called again to check the next section of the beams path.*/
    private void checkTotemInterceptInput(JButton button, int output)
    {
        //Check if the input correlates with the output
        if(findTotem(button.getIcon()) != null)
        {
            int totemInput = findTotem(button.getIcon()).getInput();

            //If the input is greater than 4 then subtract 4 to get the appropriate output value
            if(totemInput > 4)
            {
                if(totemInput - 4 == output)
                {
                    //Recursively continue to construct the beam's path
                    constructBeam(findTotem(button.getIcon()).getOutput(),
                    calculateCoordinates(searchButtonArray(button)),
                    button);
                }
            }
            //If the input is lessthan 4 then add 4 to get the appropriate output value
            else if(totemInput + 4 == output)
            {
                //Recursively continue to construct the beam's path
                constructBeam(findTotem(button.getIcon()).getOutput(),
                calculateCoordinates(searchButtonArray(button)),
                button);
            }
        }
    }
    
    /*The setTotemPositions() function iterates through the totem array finding an appropriate, random grid
    reference to set the totem at.*/
    private void setTotemPositions()
    {
        for(int i = 0; i < m_TotemArray.length - 1; i++)
        {
            findLocation(i);
        }
    }

    /*Function which searches the totem array for a specific totem using it's icon as search criteria*/
    private Totem findTotem(Icon totemIcon)
    {
        for(int i = 0; i < m_TotemArray.length; i++)
        {
            if(m_TotemArray[i].getTotemIcon() == totemIcon)
            {
                return m_TotemArray[i];
            }
        }
        return null;
    }

    //Random number generation function generating a number between 1-36 for random totem position setting
    private int generateRandomNumber()
    {
        Random randomNumber = new Random();
        int randomInt = randomNumber.nextInt(36);
        randomInt += 1;
        return randomInt;
    }

    //Recursive function which searches until an appropriate, random position is suitable for a totem to be set
    private void findLocation(int loopcount)
    {
        //Generate random number
        int randomInt = generateRandomNumber();
        //Calculate the grid coordinates using the number generated
        int[] coords = calculateCoordinates(randomInt);

        //Check the grid reference to see if a totem is already there
        if(checkArray(coords[0],coords[1]) == false)
        {
            //Check the surrounding grid references to see if a totem is already placed
            if(checkSurrounding(coords[0],coords[1]) == false)
            {
                m_TotemArray[loopcount].setButton(m_ButtonArray[randomInt - 1]);
            }
            else
            {
                //Recursively check another position
                findLocation(loopcount);
            }
        }
        else
        {
            //Recursively check another position
            findLocation(loopcount);
        }
    }

    //Function which returns the button at a specific x,y coordinate
    private JButton retrieveButton(int x, int y)
    {
        return m_ButtonArray[(((y - 1) * 6) + x) - 1];
    }

    //Button click event which alters the button, moves totems and prevents movement in specific scenarios
    public void actionPerformed(JTextArea feedback, ActionEvent e )
    {
        //Check if there is already a button selected and if it is not equal to one the that has already been selected
        if(m_boolButtonSelected && m_CurrentSelectedButton != searchButtonArray(((JButton)e.getSource())))
        {
            //A second grid position has been selected, search the button array for its index
            int secondSelectButtonIndex = searchButtonArray(((JButton)e.getSource()));
            //Calculate it's coordinates from the index
            int[] coords = calculateCoordinates(secondSelectButtonIndex);
            //Store icon and remove it from the button temporarily while it checks the selected button's surrounding
            //buttons for another totem.
            Icon tempIcon = m_ButtonArray[m_CurrentSelectedButton - 1].getIcon();
            m_ButtonArray[m_CurrentSelectedButton - 1].setIcon(null);

            //Check to see if the second selected button isn't already a totem and check the surrounding buttons for a totem.
            if(checkArray(coords[0],coords[1]) == false && checkSurrounding(coords[0],coords[1]) == false)
            {
                //Check if the totem has been moved further than one space on the grid
                if(checkMovement(m_CurrentSelectedButton, coords[0],coords[1]) == true)
                {
                    //Change the icons of the two buttons and deselect the first
                    ((JButton)e.getSource()).setIcon(tempIcon);
                    m_ButtonArray[m_CurrentSelectedButton - 1].setIcon(null);
                    m_ButtonArray[m_CurrentSelectedButton - 1].setBackground(m_DefaultColour);
                    ((JButton)e.getSource()).setBackground(m_DefaultColour);
                    m_boolButtonSelected = false;
                }
                else
                {
                    //Reset the icons of the buttons and prevent the movement, informing the player why
                    m_ButtonArray[m_CurrentSelectedButton - 1].setIcon(tempIcon);
                    m_ButtonArray[m_CurrentSelectedButton - 1].setBackground(m_DefaultColour);
                    m_boolButtonSelected = false;
                    feedback.append("You cannot move the totem that far! \n");
                }
            }
            //Totem cannot be moved to within a grid reference of another, output text that inform player of the failed move and
            //de-select active totem.
            else
            {
                m_ButtonArray[m_CurrentSelectedButton - 1].setIcon(tempIcon);
                m_ButtonArray[m_CurrentSelectedButton - 1].setBackground(m_DefaultColour);
                m_boolButtonSelected = false;
                feedback.append("The totem resists movement in that direction. \n");
            }
        }
        //If the selected button is clicked again, deselect and nullify all variables
        else if(m_boolButtonSelected && m_CurrentSelectedButton == searchButtonArray(((JButton)e.getSource())))
        {
            m_ButtonArray[m_CurrentSelectedButton - 1].setBackground(m_DefaultColour);
            m_boolButtonSelected = false;
            m_CurrentSelectedButton = 0;
        }
        //No button selected, highlight selected button and set all variables
        else
        {
            //If the selected button does not have a icon on it, do not select it
            if(((JButton)e.getSource()).getIcon() != null)
            {
                ((JButton)e.getSource()).setBackground(Color.RED);
                m_boolButtonSelected = true;
                m_CurrentSelectedButton = searchButtonArray(((JButton)e.getSource()));
            }
        }
    }
}
