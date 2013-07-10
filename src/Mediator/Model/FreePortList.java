package Mediator.Model;

import java.util.ArrayList;

public class FreePortList extends ArrayList<Integer>  {
    public FreePortList(){
        for (int i = 8000; i < 8010; i++) this.add(i);
    }
}
