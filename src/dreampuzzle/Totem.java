package dreampuzzle;

import javax.swing.JButton;
import javax.swing.Icon;

/**
 * The totem class holds all of the data regarding an individual, movable totem and provides
 * public accessor methods.
 * @author Michael
 */
public class Totem
{
    //Input and output values are a numerical value 1 - 8, 1 representing a north input/output,
    //3 representing a east input/output etc...
    private int m_Input;
    private int m_Output;
    private Icon m_TotemIcon;
    private JButton m_TotemButton;

    //Totem constructor used when the totem's button is not known
    public Totem(int i, int o, Icon ic)
    {
        m_Input = i;
        m_Output = o;
        m_TotemIcon = ic;
    }

    //Overloaded constuctor used by the start and end totems
    public Totem(int i, int o, Icon ic, JButton button)
    {
        m_Input = i;
        m_Output = o;
        m_TotemIcon = ic;
        m_TotemButton = button;

        setButton(button);
    }

    public void setButton(JButton button)
    {
        if(getTotemButton() == null)
        {
            m_TotemButton = button;
        }
        button.setIcon(getTotemIcon());
    }

    /**
     * @return the m_Input int value
     */
    public int getInput() {
        return m_Input;
    }

    /**
     * @return the m_Output int value
     */
    public int getOutput() {
        return m_Output;
    }

    /**
     * @return the m_TotemIcon as an Icon
     */
    public Icon getTotemIcon() {
        return m_TotemIcon;
    }

    /**
     * @return the m_TotemButton as a JButton
     */
    public JButton getTotemButton() {
        return m_TotemButton;
    }
}
