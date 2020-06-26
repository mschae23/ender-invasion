package de.martenschaefer.enderinvasion;

import nerdhub.cardinal.components.api.component.Component;

public interface StateComponent extends Component {

 public State value();
 public void setValue(State state);
}