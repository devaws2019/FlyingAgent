import agents.MainAgent;
import agents.MobileAgent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

import java.util.Scanner;

class Launcher {

    private static final String MAIN_CONTAINER_HOST_IP = "192.168.137.188";
    private static final int MAIN_CONTAINER_PORT = 1099;
    private static final String PLATFORM_ID = "FlyingAgents";
    private static AgentContainer mc;

    Launcher() {

        try {
            // Get a hold on JADE runtime
            Runtime rt = Runtime.instance();

            // Exit the JVM when there are no more containers around
            rt.setCloseVM(true);

            // Launch a complete platform on the 8888 port
            // create a default Profile
            Profile pMain = new ProfileImpl(MAIN_CONTAINER_HOST_IP, MAIN_CONTAINER_PORT, PLATFORM_ID);

            System.out.println("Launching a whole in-process platform..." + pMain);
            mc = rt.createMainContainer(pMain);

            AgentController receiverAgent = mc.createNewAgent(MainAgent.NAME, MainAgent.class.getName(), new Object[]{});
            receiverAgent.start();

            AgentController rma = mc.createNewAgent("rma", "jade.tools.rma.rma", new Object[0]);
            //rma.start();

            AgentController mobileAgent = mc.createNewAgent("Service-Agent", MobileAgent.class.getName(), new Object[]{});
            mobileAgent.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}