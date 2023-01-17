package Main;



public class customExceptions extends Exception{

    public customExceptions(String message){super(message);}

    public static class tooShortUser extends customExceptions{

        public tooShortUser(){
            super("Your username should be at least 4 characters");
        }
    }

    public static class tooShortPass extends customExceptions{
        public tooShortPass(){
            super("Your password you should be at least 4 characters");
        }
    }

    public static class userAlreadyExisting extends customExceptions{
        public userAlreadyExisting(){
            super("This username already exists");
        }
    }


    public static class noUserFound extends customExceptions{
        public noUserFound(){
            super("No username found to match yours");
        }
    }


}
