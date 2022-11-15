import java.awt.*;
import java.awt.event.*;

public class Prompt extends Frame {

    private Button submit;
    
	// Initialise a prompt for the user.
	//
    public Prompt() {	
		this.setLayout(new GridLayout(0,2));
		submit = new Button("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				((Frame)(evt.getSource())).dispose();
			}
		});	
	}

	// Add a submit function for the user.
	//
	public void addSubmitListener(ActionListener listener) {
		submit.addActionListener(listener);
	}    


	public void activate() {	
		this.add(submit);
		this.pack(); // Resizes to tightly fit all its components
		this.setLocationRelativeTo(null); // Centers the window on the screen
		this.setVisible(true);
    }

}
