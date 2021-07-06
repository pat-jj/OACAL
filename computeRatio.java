public class computeRatio {

    public static void main(String[] args) {
        double[] oasis_average = {12, 35, 139, 471, 1631, 7171};
        double[] oasis_worst = {28, 118, 421, 1693, 6624, 24236};
        double[] oacal_average = {7, 21, 66, 214.5, 715, 2431};
        double[] oacal_worst ={14, 42, 132, 429, 1430, 4862};
        double[] t_dcs = {35, 45, 54, 74, 80, 88};
        double[] t_mc = {22, 28, 38, 46, 54, 68};

        for (int i = 2; i < 6; i++) {
            double result_avg = average(oasis_average[i], oacal_worst[i], t_dcs[i], t_mc[i]);
            double result_wst = worst(oasis_worst[i], oacal_worst[i], t_dcs[i], t_mc[i]);
            System.out.println((i+4) + "modules: " + "avg: " + result_avg + "  wst: " + result_wst);
        }

    }

    public static double average(double oasis_avg, double oacal_worst, double t_dcs, double t_mc){
        double K = 100;
        double d = 6;
        double N = 7;
        double tau = 0.3;
        double C = 1.12 * Math.pow(10, -5);
        double R = 6;

        double result = 0;
        result =  (oasis_avg * t_dcs) / ((tau*oacal_worst*t_mc)+(R*C*K*d*N*log2(tau*oacal_worst))*1000);
        return result;
    }

    public static double worst(double oasis_worst, double oacal_worst, double t_dcs, double t_mc){
        double K = 100;
        double d = 6;
        double N = 7;
        double tau = 0.3;
        double C = 1.12 * Math.pow(10, -5);
        double R = 6;

        double result = 0;
        result =  (oasis_worst * t_dcs) / ((tau*oacal_worst*t_mc)+(R*C*K*d*N*Math.log(tau*oacal_worst))*1000);
        return result;
    }

    public static double log2(double x)
    {
        return (Math.log(x) / Math.log(2));
    }
}
