package com.example.pace;
import java.util.ArrayList;
public class Test {

    /* 11/5/2023 Function responsible for populating data structure*/
    public ArrayList<ClientModule> populateClientModuleList() {
        ArrayList<ClientModule> clientModuleList = new ArrayList<>();
        clientModuleList.add(new ClientModule("mm/dd/yyyy", R.drawable.green_circle));
        clientModuleList.add(new ClientModule("mm/dd/yyyy", R.drawable.red_circle));
        clientModuleList.add(new ClientModule("mm/dd/yyyy", R.drawable.red_circle));
        clientModuleList.add(new ClientModule("mm/dd/yyyy", R.drawable.green_circle));
        clientModuleList.add(new ClientModule("mm/dd/yyyy", R.drawable.red_circle));
        clientModuleList.add(new ClientModule("mm/dd/yyyy", R.drawable.green_circle));
        clientModuleList.add(new ClientModule("mm/dd/yyyy", R.drawable.green_circle));
        clientModuleList.add(new ClientModule("mm/dd/yyyy", R.drawable.green_circle));
        return clientModuleList;
    }

}
