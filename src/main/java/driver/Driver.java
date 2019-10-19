package driver;

import image.ImageProcessor;

import java.io.File;

public class Driver {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("help")) {
            printHelp();
        } else if (args.length != 6) {
            System.out.println(args.length);
            for (String s : args) {
                System.out.println(s);
            }
            System.out.println("Please use the program like so : java HoleProcessor imageFolder maskNumber [4|8] z epsilon [fast|slow]");
        } else if (!(args[2].equals("4") || args[2].equals("8"))) {
            System.out.println("Please use 4 or 8 only as the connectivity types");
        } else if (!(args[5].equals("fast") || (args[5]).equals("slow"))) {
            System.out.println("Please use 0 or 1 for the method type. Run 'java HoleProcessor help' for information on each argument");
        } else {
            RunApplication(args);
        }
    }

    private static void printHelp() {
        System.out.println("Please use the program like so : java HoleProcessor imageFolder maskNumber [4|8] z epsilon [0|1]");
        System.out.println("imageFolder : The location of the folder that contains the image. Ensure the folder contains an jpeg called 'image'");
        System.out.println("maskNumber : The mask number to use in the folder. Ensure the mask files are named 'image_maskX'");
        System.out.println("[4|8] : Defines whether to use 4 or 8 connectivity to fill the hole. Use the value of either 4 or 8.");
        System.out.println("z : The distance factor. The higher the value, the less effect values further away from a hole pixel have on it's resultant colour");
        System.out.println("epsilon : A small value of epsilon that must be > 0 to prevent the hole filler algorithm from performing 'divide by 0s'");
        System.out.println("[fast|slow] : Defines whether to use fast hole filling (less accurate) or slow hole filling (accurate)'");
    }

    private static void RunApplication(String[] args) {
        // REAL

        int connectivityType = Integer.parseInt(args[2]);
        double z = Double.parseDouble(args[3]);
        double epsilon = Double.parseDouble(args[4]);
        boolean isFast = args[5].equals("fast");

        String imageLocation = args[0] + "\\image.jpg";
        String maskLocation = args[0] + "\\image_mask" + args[1] + ".jpg";
        String outputLocation = args[0] + "\\outputs\\";
        String outputName = String.format("filled_img_mask%s_c%d_z%.2f_e%.3f_"+args[5]+".jpg", args[1], connectivityType, z, epsilon);


        // DEBUG
        /*
        int connectivityType = 4;
        double z = 4;
        double epsilon = 0.001;
        int methodType = 1;
        int maskNumber = 2;
        String methodString = "fast";
        String folderLocation = "C:\\Users\\Harry\\Desktop\\imagepacks\\2";
        String imageLocation = folderLocation + "\\image.jpg";
        String maskLocation = folderLocation + "\\image_mask" + maskNumber + ".jpg";
        String outputLocation = folderLocation + "\\outputs\\";
        String outputName = String.format("filled_img_mask%d_c%d_z%.2f_e%.3f_"+methodString+".jpg", maskNumber, connectivityType, z, epsilon);
        boolean isFast = args[5].equals("fast");
         */

        new File(outputLocation).mkdir();

        ImageProcessor processor = new ImageProcessor();

        if (connectivityType == 4) {
            processor.fillHoleFourConnectivity(imageLocation, maskLocation, outputLocation, outputName, z, epsilon, isFast);
        } else {
            processor.fillHoleEightConnectivity(imageLocation, maskLocation, outputLocation, outputName, z, epsilon, isFast);
        }
    }
}
