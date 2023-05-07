import java.util.Scanner;

public class DataCenterServiceClean {

  public static class Company {
    private DataCenter[] dataCenters;

    public Company(DataCenter[] dataCenters) {
      this.dataCenters = dataCenters;
    }

    public DataCenter[] getDataCenters() {
      return dataCenters;
    }

    public void setDataCenters(DataCenter[] dataCenters) {
      this.dataCenters = dataCenters;
    }
  }

  public static class DataCenter {

    private Server[] servers;
    private int ri; // число перезапусков i-го дата-центра
    private int ai; // число рабочих (не выключенных) серверов на текущий момент в i-м дата-центре

    public DataCenter(Server[] servers, int ri, int ai) {
      this.servers = servers;
      this.ri = ri;
      this.ai = ai;
    }

    public Server[] getServers() {
      return servers;
    }

    public void setServers(Server[] servers) {
      this.servers = servers;
    }

    public int getRi() {
      return ri;
    }

    public void setRi(int ri) {
      this.ri = ri;
    }

    public int getAi() {
      return ai;
    }

    public void setAi(int ai) {
      this.ai = ai;
    }
  }

  public static class Server {
    private boolean enabled;

    public boolean isEnabled() {
      return enabled;
    }

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }
  }

  public static void main(String[] args) {

    int n; // число дата-центров
    int m; // число серверов
    int q; // число событий

    Scanner sc1 = new Scanner(System.in);
    n = sc1.nextInt();
    m = sc1.nextInt();
    Company company;
    {
      DataCenter[] dataCenters = new DataCenter[n];
      for (int i = 0; i < n; i++) {
        Server[] servers = new Server[m];
        for (int j = 0; j < m; j++) {
          servers[j] = new Server();
          servers[j].setEnabled(true);
        }
        dataCenters[i] = new DataCenter(servers, 0, m);
      }
      company = new Company(dataCenters);
    }
    q = sc1.nextInt();
    sc1.nextLine();
    String[] events = new String[q];
    for (int i = 0; i < q; i++) {
      events[i] = sc1.nextLine();
    }

    String delimSpace = " ";
    for (String event: events) {
      if (event.contains("DISABLE")) {
        String[] values  = event.split(delimSpace);
        int i = Integer.parseInt(values[1]) - 1;
        int j = Integer.parseInt(values[2]) - 1;
        DataCenter[] dataCenters1 = company.getDataCenters();
        if (dataCenters1[i].getServers()[j].isEnabled()) {
          Server[] servers1 = dataCenters1[i].getServers();
          servers1[j].setEnabled(false);
          int ai = dataCenters1[i].getAi() - 1;
          if (ai >= 0) {
            dataCenters1[i].setAi(ai);
          }
          dataCenters1[i].setServers(servers1);
          company.setDataCenters(dataCenters1);
        }
      }
      if (event.contains("RESET")) {
        String[] values  = event.split(delimSpace);
        int i = Integer.parseInt(values[1]) - 1;
        DataCenter[] dataCenters1 = company.getDataCenters();
        int ri = dataCenters1[i].getRi() + 1;
        dataCenters1[i].setRi(ri);
      }

      if (event.contains("GETMAX")) {
        DataCenter[] dataCenters1 = company.getDataCenters();
        int dataCenterMaxIndex = 0;
        int currentMax = dataCenters1[0].getAi() * dataCenters1[0].getRi();
        for (int i = 0; i < n; i++) {
          int max = dataCenters1[i].getAi() * dataCenters1[i].getRi();
          if (currentMax < max) {
            dataCenterMaxIndex = i;
            currentMax = max;
          }
        }

        System.out.println((dataCenterMaxIndex + 1));
      }

      if (event.contains("GETMIN")) {
        DataCenter[] dataCenters1 = company.getDataCenters();
        int dataCenterMinIndex = 0;
        int currentMin = dataCenters1[0].getAi() * dataCenters1[0].getRi();
        for (int i = 0; i < n; i++) {
          int min = dataCenters1[i].getAi() * dataCenters1[i].getRi();
          if (min < currentMin) {
            dataCenterMinIndex = i;
            currentMin = min;
          }
        }

        System.out.println((dataCenterMinIndex + 1));
      }

    }
  }
}
