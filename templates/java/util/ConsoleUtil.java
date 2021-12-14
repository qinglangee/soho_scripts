import java.util.Scanner;

public class ConsoleUtil{
    Scanner sc = new Scanner(System.in);
    public static final String ERROR_INPUT = "Error input.";
    
    // post: asks the user a question, forcing an answer of "y" or "n";
    //       returns true if the answer was yes, returns false otherwise
    public static boolean yesTo(Scanner console, String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

    
    private void info(CharSequence content){
        System.out.println(content);
    }
    private void prompt(CharSequence content){
        System.out.print(content);
    }
    
    /**
     * get user input integer
     * @param prompt prompt infomation
     * @return integer which user inputed
     */
    public int inputInt(String prompt){
        prompt(prompt + " ");
        int num;
        try{
            num = Integer.parseInt(sc.nextLine());
            return num;
        }catch(Exception e){
            info(ERROR_INPUT);
            return inputInt(prompt);
        }
    }

    /**
     * get user input double
     * @param prompt prompt infomation
     * @return double which user inputed
     */
    public double inputDouble(String prompt){
        prompt(prompt + " ");
        double num;
        try{
            num = Double.parseDouble(sc.nextLine());
            return num;
        }catch(Exception e){
            info("Please enter a double number.");
            return inputInt(prompt);
        }
    }
    
    /**
     * get user input from console
     * @param prompt prompt infomation
     * @param options options to input
     * @return user input
     */
    public String input(String prompt, CharSequence ... options){
        prompt(prompt + " ");
        String response = sc.nextLine().trim();;
        while(options != null && options.length > 0 && !contains(options, response)){
            info(ERROR_INPUT);
            prompt(prompt);
            response = sc.nextLine().trim();
        }
        return response;
    }

    /**
     * prompt user to choose yes or no
     * @param prompt prompt infomation
     * @return true if user input yes or y
     */
    public boolean yesTo(String prompt){
        String response = input(prompt, "yes", "no", "y", "n");
        return response.equals("yes") || response.equals("y");
    }

    /**
     * Wait for user to press any key
     */
    public void waitKey(String prompt){
        waitEnter(prompt);
    }

    /**
     * wait for user input enter key
     */
    public void waitEnter(String prompt){
        prompt(prompt);
        sc.nextLine();
    }

    /**
     * check if str is in the options array
     * @param options string array
     * @param str string to check
     * @return true if str is in options
     */
    private boolean contains(CharSequence[] options, CharSequence str){
        if(options == null || str == null){
            return false;
        }
        for(CharSequence option : options){
            if(str.equals(option)){
                return true;
            }
        }
        return false;
    }
}