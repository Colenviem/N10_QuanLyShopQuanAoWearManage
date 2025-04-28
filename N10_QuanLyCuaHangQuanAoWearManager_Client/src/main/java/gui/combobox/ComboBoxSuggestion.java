package gui.combobox;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class ComboBoxSuggestion<E> extends JComboBox<E> {

    public ComboBoxSuggestion() {
        setUI(new ComboSuggestionUI());
    }
    
     // Constructor với DefaultComboBoxModel
    public ComboBoxSuggestion(DefaultComboBoxModel<E> model) {
        super(model);
        setUI(new ComboSuggestionUI());
    }
}
