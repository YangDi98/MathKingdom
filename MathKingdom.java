/*CSCI1100 - Project
  This project is a math game called Math Kingdom designed for students in grade 1 to grade 4
  Yang Di B00777222,Le Wang B00761974,Jia Yu B00785728,*/
import java.util.Scanner;
import java.util.Random;
public class MathKingdom{
   public static void main(String args[]){
      //create a Scanner object
      Scanner kb = new Scanner(System.in);
      //ask how many people are playing
      System.out.print("How many people are playing? Enter 1 or 2: ");
      int people = kb.nextInt();
      //ask for grade
      System.out.print("Please enter your grade, enter 1,2,3,or 4: ");
      int grade = kb.nextInt();
      //if one person is playing, only ask for one name
      if (people == 1){
         System.out.print("Please enter your name: ");
         String name = kb.next();
         System.out.println("Welcome to Math Kingdom! "+name);
         //if one person is playing, perform the one person game
         System.out.println("(King talking)\"My daughter is kidnapped by a monster in a castle at the border of our kingdom. I hope you can save my daughter!\"");
         //player has two lives to start with, he collects keys on his way. 
         int lives = 2;
         int key = 0;
         //first get the sword or not
         boolean sword = sword_1(grade);
         //fight with the first beast
         lives = beast_1(lives,grade,sword);
         //if the player still has lives left, keep playing
         if (lives!=0){
            //after the last step, if the player still has lives left, it means that he conquered the beast and got a key
            key = key + 1;
            //tell the player how many lives he has
            System.out.println("You have "+lives+" lives left!");
            //play has a chance to get a boat ticket to cross the river
            boolean ticket = ticket_1(grade);
            if (ticket == false){
               //if the play did not get the ticket, he has to swim to cross the river and he will encounter a demon afterwards
               swim_1(grade);
               lives = demon_1(lives,grade);
               //after this step, if the play still has lives left
               if(lives!=0){
                  lives = forest(lives,grade);
                  if(lives!=0){
                     System.out.println("Five guards block the way. You have to fight against them!");
                     key = FiveDemon(grade,lives,key);
                     //the player gets chance to fight against the beast boss only if he collects 5 or more keys
                     if(key >= 5){
                        System.out.println("You get to fight against the beast boss!");
                        lives = boss(grade,lives);
                        if(lives !=0){
                           System.out.println("Congratulations! You win!");
                           System.out.println("(Princess talking): Thank you for saving my life!");
                        }
                     }
                     else{
                        System.out.println("On no! You do not have 5 keys! You can not save the princess!");
                     }
                  }
               }
            }
            //if the person has ticket
            if(ticket == true){
               if(lives!=0){
                  lives = forest(lives,grade);
                  if(lives!=0){
                     System.out.println("Five guards block the way. You have to fight against them!");
                     key = FiveDemon(grade,lives,key);
                     //the player gets chance to fight against the beast boss only if he collects 5 or more keys
                     if(key >= 5){
                        System.out.println("You get to fight against the beast boss!");
                        lives = boss(grade,lives);
                        if(lives !=0){
                           System.out.println("Congratulations! You win!");
                           System.out.println("(Princess talking): Thank you for saving my life!");
                        }
                     }
                     else{
                        System.out.println("On no! You do not have 5 keys! You can not save the princess!");
                     }
                  }
               }   
            }
         }
         System.out.println("See you next time");
      }
      //if two people are playing, they take turns can compete who gets to save the princess first
      else{
         System.out.print("Please enter your name: ");
         String name1 = kb.next();
         String name2 = kb.next();
         System.out.println("Welcome to Math Kingdom! "+name1+" and "+name2+" !");
         System.out.println("(King talking)\"My daughter is kidnapped by a monster in a castle at the border of our kingdom. I hope you can save my daughter!\"");
         System.out.println("The one who gets to save the princess first wins! The first question will determine who gets to go first");
         System.out.println("After reading the questions, who gets the answer first please hit yout number on the keyboard.(1 for "+name1+" and 2 for "+name2);
         //call the method WhoFirst
         int first = WhoFirst(grade,name1,name2);
         //if the first person gets the turn
         int lives1 = 2;
         int lives2 = 2;
         int key1 = 0;
         int key2 = 0;
         //first person go first
         if (first == 1){
            System.out.println("it is "+name1+"\'s turn");
            boolean sword1 = sword_1(grade);
            System.out.println("It is "+name2+"\'s turn");
            boolean sword2 = sword_1(grade);
            System.out.println("it is "+name1+"\'s turn");
            lives1 = beast_1(lives1,grade,sword1);
            System.out.println("It is "+name2+"\'s turn");
            lives2 = beast_1(lives2,grade,sword2);
            //if both 1 and 2 are still alive
            if (lives1 != 0 && lives2 != 0){
               key1 = key1 + 1;
               System.out.println(name1+", you have "+lives1+" lives left!");
               key2 = key2 + 1;
               System.out.println(name2+", you have "+lives2+" lives left!");
               System.out.println("It is "+name1+"\'s turn");
               lives1 = forest(lives2,grade);
               System.out.println("It is "+name2+"\'s turn");
               lives2 = forest(lives2,grade);
               if(lives1 != 0&&lives2 != 0){
                  System.out.println("It is "+name1+"\'s turn");
                  System.out.println("Five guards block the way. You have to fight against them!");
                  key1 = FiveDemon(grade,lives1,key1);
                  System.out.println("It is "+name2+"\'s turn");
                  System.out.println("Five guards block the way. You have to fight against them!");
                  key2 = FiveDemon(grade,lives2,key2);
                  //if the first person gets 5 keys
                  if(key1 >= 5){
                     System.out.println("You get to fight against the beast boss!");
                     lives1 = boss(grade,lives1);
                     //if first person gets to save the princess , he wins
                     if(lives1 != 0){
                        System.out.println(name1+" wins! You saved the princess!");
                     }
                     else{
                        if(lives2!=0&&key2>=5){
                           System.out.println("You get to fight against the beast boss!");
                           lives2 = boss(grade,lives2);
                           if(lives2 != 0){
                              System.out.println(name2+" wins! You saved the princess!");
                           }
                           else{
                              System.out.println("Sorry, you both did not get to save the princess, see you next time!");
                           }
                        }
                     }
                  }
                  //if only the second person gets to the last step
                  else if(key1<5&&key2>=5){
                     System.out.println("You get to fight against the beast boss!");
                     lives2 = boss(grade,lives2);
                     if(lives2 != 0){
                        System.out.println(name2+" wins! You saved the princess!");
                     }
                     else{
                        System.out.println("Sorry, you both did not get to save the princess, see you next time!");
                     }
                  
                  }
               }
               //if only one person still has life, then only perform the game the that person
               else if(lives1 !=0 && lives2 == 0){
                  System.out.println("Five guards block the way. You have to fight against them!");
                  key1 = FiveDemon(grade,lives1,key1);
                  if(key1 >= 5){
                     System.out.println("You get to fight against the beast boss!");
                     lives1 = boss(grade,lives1);
                     if(lives1 !=0){
                        System.out.println("Congratulations!"+name1+" win!");
                        System.out.println("(Princess talking): Thank you for saving my life!");
                     }
                  }  
               }
               else if(lives1 == 0&&lives2 != 0){
                  System.out.println("Five guards block the way. You have to fight against them!");
                  key2 = FiveDemon(grade,lives2,key2);
                  if(key2 >= 5){
                     System.out.println("You get to fight against the beast boss!");
                     lives2 = boss(grade,lives2);
                     if(lives2 !=0){
                        System.out.println("Congratulations!"+name2+" win!");
                        System.out.println("(Princess talking): Thank you for saving my life!");
                     }
                  }  
               }
               //if on one has lives left
               else if(lives1 == 0&&lives2 == 0){
                  System.out.println("Sorry, you both failed, see you next time!");
               }
            }
            //if after the beast, only one person still alive, then only run the rest of the game for him
            else if (lives1 == 0 && lives2 != 0){
               System.out.println(name1+", sorry, nice try!");
               System.out.println(name2+", keep going!");
               lives2 = forest(lives2,grade);
               if(lives2!=0){
                  System.out.println("Five guards block the way. You have to fight against them!");
                  key2 = FiveDemon(grade,lives2,key2);
                  if(key2 >= 5){
                     System.out.println("You get to fight against the beast boss!");
                     lives2 = boss(grade,lives2);
                     if(lives2 !=0){
                        System.out.println("Congratulations! You win!");
                        System.out.println("(Princess talking): Thank you for saving my life!");
                     }
                     else{
                        System.out.println("Sorry, see you next time");
                     }
                  }
               }
               
            }
            else if (lives1 != 0 && lives2 == 0){
               System.out.println(name2+", sorry, nice try!");
               System.out.println(name1+", keep going!");
               lives1 = forest(lives1,grade);
               if(lives1!=0){
                  System.out.println("Five guards block the way. You have to fight against them!");
                  key1 = FiveDemon(grade,lives1,key1);
                  if(key1 >= 5){
                     System.out.println("You get to fight against the beast boss!");
                     lives1 = boss(grade,lives1);
                     if(lives1 !=0){
                        System.out.println("Congratulations! You win!");
                        System.out.println("(Princess talking): Thank you for saving my life!");
                     }
                     else{
                        System.out.println("Sorry, see you next time");
                     }
                  }
               }
            }
            else if(lives1 == 0&& lives2 == 0){
               System.out.println(name1+" and "+name2+", sorry, nice try, see you next time!");
            }
         }
         //almost the same, only when the second person gets to play first
         if(first == 2){
            System.out.println("it is "+name2+"\'s turn");
            boolean sword2 = sword_1(grade);
            System.out.println("It is "+name1+"\'s turn");
            boolean sword1 = sword_1(grade);
            System.out.println("it is "+name2+"\'s turn");
            lives2 = beast_1(lives1,grade,sword1);
            System.out.println("It is "+name1+"\'s turn");
            lives1 = beast_1(lives2,grade,sword2);
            if (lives1 != 0 && lives2 != 0){
               key2 = key2 + 1;
               System.out.println(name2+", you have "+lives1+" lives left!");
               key1 = key1 + 1;
               System.out.println(name1+", you have "+lives2+" lives left!");
               System.out.println("It is "+name2+"\'s turn");
               lives2 = forest(lives2,grade);
               System.out.println("It is "+name1+"\'s turn");
               lives1 = forest(lives2,grade);
               if(lives1 != 0&&lives2 != 0){
                  System.out.println("It is "+name2+"\'s turn");
                  System.out.println("Five guards block the way. You have to fight against them!");
                  key2 = FiveDemon(grade,lives2,key2);
                  System.out.println("It is "+name1+"\'s turn");
                  System.out.println("Five guards block the way. You have to fight against them!");
                  key1 = FiveDemon(grade,lives1,key1);
                  if(key2 >= 5){
                     System.out.println("You get to fight against the beast boss!");
                     lives2 = boss(grade,lives2);
                     if(lives2 != 0){
                        System.out.println(name2+" wins! You saved the princess!");
                     }
                     else{
                        System.out.println("You get to fight against the beast boss!");
                        lives1 = boss(grade,lives1);
                        if(lives1 != 0){
                           System.out.println(name1+" wins! You saved the princess!");
                        }
                        else{
                           System.out.println("Sorry, you both did not get to save the princess, see you next time!");
                        }
                     }
                  }
                  else{
                     if(lives1!=0&&key1>=5){
                        System.out.println("You get to fight against the beast boss!");
                        lives1 = boss(grade,lives1);
                        if(lives1 != 0){
                           System.out.println(name1+" wins! You saved the princess!");
                        }
                        else{
                           System.out.println("Sorry, you both did not get to save the princess, see you next time!");
                        }
                     }
                  }
               
               }
               else if(lives1 !=0 && lives2 == 0){
                  System.out.println("Five guards block the way. You have to fight against them!");
                  key1 = FiveDemon(grade,lives1,key1);
                  if(key1 >= 5){
                     System.out.println("You get to fight against the beast boss!");
                     lives1 = boss(grade,lives1);
                     if(lives1 !=0){
                        System.out.println("Congratulations!"+name1+" win!");
                        System.out.println("(Princess talking): Thank you for saving my life!");
                     }
                  }  
               }
               else if(lives1 == 0&&lives2 != 0){
                  System.out.println("Five guards block the way. You have to fight against them!");
                  key2 = FiveDemon(grade,lives2,key2);
                  if(key2 >= 5){
                     System.out.println("You get to fight against the beast boss!");
                     lives2 = boss(grade,lives2);
                     if(lives2 !=0){
                        System.out.println("Congratulations!"+name2+" win!");
                        System.out.println("(Princess talking): Thank you for saving my life!");
                     }
                  }  
               }
               else if(lives1 == 0&&lives2 == 0){
                  System.out.println("Sorry, you both failed, see you next time!");
               }
            }
            
            else if (lives1 == 0 && lives2 != 0){
               System.out.println(name1+", sorry, nice try!");
               System.out.println(name2+", keep going!");
               lives2 = forest(lives2,grade);
               if(lives2!=0){
                  System.out.println("Five guards block the way. You have to fight against them!");
                  key2 = FiveDemon(grade,lives2,key2);
                  if(key2 >= 5){
                     System.out.println("You get to fight against the beast boss!");
                     lives2 = boss(grade,lives2);
                     if(lives2 !=0){
                        System.out.println("Congratulations! You win!");
                        System.out.println("(Princess talking): Thank you for saving my life!");
                     }
                     else{
                        System.out.println("Sorry, see you next time");
                     }
                  }
               }
               
            }
            else if (lives1 != 0 && lives2 == 0){
               System.out.println(name2+", sorry, nice try!");
               System.out.println(name1+", keep going!");
               lives1 = forest(lives1,grade);
               if(lives1!=0){
                  System.out.println("Five guards block the way. You have to fight against them!");
                  key1 = FiveDemon(grade,lives1,key1);
                  if(key1 >= 5){
                     System.out.println("You get to fight against the beast boss!");
                     lives1 = boss(grade,lives1);
                     if(lives1 !=0){
                        System.out.println("Congratulations! You win!");
                        System.out.println("(Princess talking): Thank you for saving my life!");
                     }
                     else{
                        System.out.println("Sorry, see you next time");
                     }
                  }
               }
            }
            else if(lives1 == 0&& lives2 == 0){
               System.out.println(name1+" and "+name2+", sorry, nice try, see you next time!");
            }  
         }
      }
   }
   //this method is for getting sword (one player)
   public static boolean sword_1(int grade){
      boolean sword = true;
      //create a Scanner object and a Random object
      Scanner kb = new Scanner(System.in);
      Random num = new Random();
      System.out.println("You only have two lives!\nHere comes a math question!");
      //the question is an addition question, numbers are randomly generated based on what grade the user is in
      int n1,n2,answer;
      if (grade == 1){
         n1 = num.nextInt(10);
         n2 = num.nextInt(10);
      }
      else if(grade == 2){
         n1 = num.nextInt(10);
         n2 = num.nextInt(20)+10;
      }
      else if(grade == 3){
         n1 = num.nextInt(30);
         n2 = num.nextInt(100)+100;
      }
      else{
         n1 = num.nextInt(3000)+1000;
         n2 = num.nextInt(3000)+1000;
      }
      System.out.print(n1+" + "+n2+"= ");
      answer = kb.nextInt();
         //if the answer is correct, the player get a sword
      if (n1+n2==answer){
         System.out.println("Congratulations! You get a sword!");
      }
      else{
         System.out.println("Sorry! No sword!");
         sword = false;
      }
      //this method will return if the sword is gained as a boolean value
      return sword;
   }
   
   //this method is for fighting with the first beast with sword and it returns how many lives left(one person)
   public static int beast_1(int lives,int grade,boolean sword){
      Scanner kb = new Scanner(System.in);
      Random num = new Random();
      System.out.println("You encountered a beast! Use your sword to fight! If you do not have a sword, good luck!");
      //here are three questions about addition and subtraction, numbers are generated randomly based on player's grade
      int n1,n2,n3,n4,n5,n6,n7,n8;
      if (grade == 1){
         n1 = num.nextInt(10);
         n2 = num.nextInt(20);
         n3 = num.nextInt(20);
         n4 = num.nextInt(10);
         n5 = num.nextInt(20);
         n6 = num.nextInt(20);
         n7 = num.nextInt(30);
         n8 = num.nextInt(30);
      }
      else if(grade == 2){
         n1 = num.nextInt(20)+10;
         n2 = num.nextInt(30)+10;
         n3 = num.nextInt(90)+10;
         n4 = num.nextInt(50)+10;
         n5 = num.nextInt(60)+10;
         n6 = num.nextInt(90)+10;
         n7 = num.nextInt(80);
         n8 = num.nextInt(90);
      }
      else if(grade == 3){
         n1 = num.nextInt(200)+100;
         n2 = num.nextInt(200);
         n3 = num.nextInt(300)+100;
         n4 = num.nextInt(300);
         n5 = num.nextInt(300);
         n6 = num.nextInt(400)+100;
         n7 = num.nextInt(500)+100;
         n8 = num.nextInt(600);
      }
      else{
         n1 = num.nextInt(5000)+1000;
         n2 = num.nextInt(500)+100;
         n3 = num.nextInt(600);
         n4 = num.nextInt(4000)+1000;
         n5 = num.nextInt(5000)+1000;
         n6 = num.nextInt(6000)+1000;
         n7 = num.nextInt(7000)+1000;
         n8 = num.nextInt(600);
      }
      System.out.print(Math.max(n1,n2)+" - "+Math.min(n1,n2)+"= ");
      int answer = kb.nextInt();
      //because at this point the player is fighting, if the answer is incorrect, he loses one life
      if (answer != (Math.max(n1,n2)-Math.min(n1,n2))){
         System.out.println("Oh no! Answer incorrect!");
         lives = lives - 1;
      }
      //if the player still has lives, keep playing
      if (lives!=0){
         System.out.print(n3+" + "+n4+"= ");
         answer = kb.nextInt();
         if(answer != n3+n4){
            System.out.println("Oh no! Answer incorrect!");
            lives = lives - 1;
         }
      }
      if (lives != 0){
         System.out.print(Math.max(n5,n6)+" - "+Math.min(n5,n6)+"= ");
         answer = kb.nextInt();
         if (answer!=Math.max(n5,n6)-Math.min(n5,n6)){
            System.out.println("Oh no! Answer incorrect!");
            lives = lives - 1;
         }
      }
      //if the player did not get the sword, fighting will be harder, so he has another question to answer
      if (lives!=0 && sword == false){
         System.out.print(n7+" + "+n8+"= ");
         answer = kb.nextInt();
         if (answer!=n7+n8){
            System.out.println("Oh no! Answer incorrect!");
            lives = lives - 1;
         }
      }
      //if at this stage, the player still has lives, he will get a key, and this method will return the number of key he has so far
      if (lives != 0){
         System.out.println("Congratulations! You win! You get a key!");
         return lives;
      }
      //if the last if is not entered, it means the number of life is zero and the game is over
      System.out.println("Game over");
      return lives;
   }
   //this method is about getting ticket for boat to cross the river
   public static boolean ticket_1(int grade){
      System.out.println("There is a river in front of you! Answer questions to get boat ticket!");
      Scanner kb = new Scanner(System.in);
      Random num = new Random();
      boolean ticket = true;
      char answer;
      //for grade 1 and grade 2, multiple choice questions, word quesions
      if (grade == 1){
         System.out.println("How do you make 4? Enter a,b,c,or d");
         System.out.print("a.3+1\tb.1+2\tc.2+3\td.2+2");
         answer = kb.next().charAt(0);
         //if one question is not answered correctly, the player do not get the ticket
         if (answer != 'a'){
            System.out.println("Oh no! Answer incorrect!");
            ticket = false;
            return ticket;
         }
         else{
            System.out.println("How do you make 3? Enter a,b,c,or d");
            System.out.print("a.2+2\tb.3+1\tc.2+3\td.1+2 ");
            answer = kb.next().charAt(0);
            if (answer != 'd'){
               System.out.println("Oh no! Answer incorrect!");
               ticket = false;
               return ticket;
            }
         }
      }
      else if (grade == 2){
         System.out.println("What number has 1 ten and 9 ones? Enter a,b,c,or d");
         System.out.print("a.109\tb.91\tc.19\td.90 ");
         answer = kb.next().charAt(0);
         if (answer != 'c'){
            System.out.println("Oh no! Answer incorrect!");
            ticket = false;
            return ticket;
         }
         else{
            System.out.println("What number has 2 tens and 1 one?");
            System.out.print("a.21\tb.201\tc.12\td.120 ");
            answer = kb.next().charAt(0);
            if (answer!='a'){
               System.out.println("Oh no! Answer incorrect!");
               ticket = false;
               return ticket;
            }
         }
      }
      //for grade 3, multiplicity questions, with in 10
      else if (grade == 3){
         int answer3,n1,n2;
         for (int i = 0;i<2;i++){
            n1 = num.nextInt(10);
            n2 = num.nextInt(10);
            System.out.print(n1+" * "+n2+" = ");
            answer3 = kb.nextInt();
            if (answer3!=n1*n2){
               System.out.println("Oh no! Answer incorrect!");
               ticket = false;
               return ticket;
              
            }
         }
      }
      //for grade 4, multiplicity questions, with in 20
      else{
         int answer4,n1,n2;
         for (int i = 0;i<2;i++){
            n1 = num.nextInt(10);
            n2 = num.nextInt(10)+10;
            System.out.print(n1+" * "+n2+" = ");
            answer4 = kb.nextInt();
            if (answer4!=n1*n2){
               System.out.println("Oh no! Answer incorrect!");
               ticket = false;
               return ticket;
               
            }
         }
      }
      //it it gets to this stage, it means the player get the ticket
      System.out.println("Congratulations! You get the ticket! You can cross the river safely!");
      return ticket;
   }
   
   //this method is for if the player did not get ticket and swim to cross the river
   public static void swim_1(int grade){
      System.out.println("You have to swim to cross the river!");
      Scanner kb = new Scanner(System.in);
      Random num = new Random();
      boolean swim = false;
      //for grade 1, subtraction questions, with in 10
      if (grade == 1){
         //player has to answer until he is right, to cross the river
         while(swim == false){
            int n1 = num.nextInt(10);
            int n2 = num.nextInt(10);
            System.out.print(Math.max(n1,n2)+" - ? = "+Math.min(n1,n2));
            int answer = kb.nextInt();
            if (answer == (Math.max(n1,n2)-Math.min(n1,n2))){
               swim = true;
            }
         }
      }
      //for grade 2, addition questions, with in 200
      else if(grade == 2){
         while (swim == false){
            int n1 = num.nextInt(100)+100;
            int n2 = num.nextInt(100)+100;
            System.out.print(n1+" + "+n2+" = ");
            int answer = kb.nextInt();
            if (answer == n1+n2){
               swim = true;
            }
         }
      }
      //for grade 2,division questions, with in 10
      else if(grade == 3||grade == 4){
         while (swim == false){
            int n1 = num.nextInt(10);
            int n2 = num.nextInt(10);
            int product = n1*n2;
            System.out.print(product+" divide by "+n1+" is ");
            int answer = kb.nextInt();
            if (answer == n2){
               swim = true;
            }
         }
      }
      System.out.println("You crossed the river!");
   }
   
   //this method is for swimming to cross the river, but when two players are playing the game
   public static boolean swim_2(int grade){
      System.out.println("You have to swim to cross the river!");
      Scanner kb = new Scanner(System.in);
      Random num = new Random();
      boolean swim = false;
      //for grade 1, subtraction questions, with in 10
      if (grade == 1){
         //player has to answer until he is right, to cross the river
         
         int n1 = num.nextInt(10);
         int n2 = num.nextInt(10);
         System.out.print(Math.max(n1,n2)+" - ? = "+Math.min(n1,n2));
         int answer = kb.nextInt();
         if (answer == (Math.max(n1,n2)-Math.min(n1,n2))){
            swim = true;
         }
         
      }
      //for grade 2, addition questions, with in 200
      else if(grade == 2){
         
         int n1 = num.nextInt(100)+100;
         int n2 = num.nextInt(100)+100;
         System.out.print(n1+" + "+n2+" = ");
         int answer = kb.nextInt();
         if (answer == n1+n2){
            swim = true;
         }
         
      }
      //for grade 2,division questions, with in 10
      else if(grade == 3||grade == 4){
         
         int n1 = num.nextInt(10);
         int n2 = num.nextInt(10);
         int product = n1*n2;
         System.out.print(product+" divide by "+n1+" is ");
         int answer = kb.nextInt();
         if (answer == n2){
            swim = true;
         }
         
      }
      if (swim == true){
         System.out.println("You crossed the river!");
      }
      return swim;
   
   }
   //this method is for the water demon (if the player swim to cross the river)
   public static int demon_1(int lives, int grade){
      Scanner kb = new Scanner(System.in);
      Random num = new Random();
      System.out.println("You crossed the river and you encountered a demon! Answer questions to conquer it!");
      int n1,n2,answer;
      //there are three questions
      for (int i = 0;i<3;i++){
         //for grade 1 and grade 2, addition questions in words
         if (grade == 1 || grade == 2){
            if (grade == 1){
               n1 = num.nextInt(10);
               n2 = num.nextInt(10);
            }
            else{
               n1 = num.nextInt(20)+10;
               n2 = num.nextInt(10)+10;
            }
            System.out.print("what is "+n1+" more than "+n2+" ? ");
            answer = kb.nextInt();
            //if the answer is incorrect, the player loses one life
            if (answer != n1+n2){
               System.out.println("Oh no! Answer incorrect!");
               lives = lives - 1;
               //check if the player still has lives left, if not, game is over
               if (lives == 0){
                  System.out.println("Game over");
                  return lives;
               }
            }
         }
         //for grade 3 and grade 4, multiplicity questions, with in 10
         else{
            n1 = num.nextInt(10);
            n2 = num.nextInt(10);
            System.out.print(n1+" * "+n2+" = ");
            answer = kb.nextInt();
            if (answer != n1*n2){
               System.out.println("Oh no! Answer incorrect!");
               lives = lives - 1;
               if (lives == 0){
                  System.out.println("Game over");
                  return lives;
               }
            }
         }
      }
      System.out.println("You conquered the beast!");
      return lives;
   }
   
   //this method will determine who gets to enter the game first
   public static int WhoFirst(int grade,String name1,String name2){
      Scanner kb = new Scanner(System.in);
      Random num = new Random();
      //for grade 1 and grade 2, give them counting problem to start
      int first;
      int correct;
      if (grade == 1 || grade == 2){
         int number = num.nextInt(20);
         int [] a = new int [5];
         a[0] = number;
         for (int i = 1;i < 5;i++){
            a[i] = a[i-1]+5;
         }
         System.out.print("What is the missing number in the following sequence of numbers  ");
         for (int i = 0;i<5;i++){
            if (i == 2){
               System.out.print(" ? ");
            }
            else{
               System.out.print(a[i]+" ");
            }
         }
         correct = a[2];
      }
      //for grade 3 and grade 4, given them a rounding problem
      else{
         int number = num.nextInt(20)+10;
         int temp = number;
         System.out.println("What does "+number+" round to the nearst ones?");
         while(temp>=10){
            temp = temp - 10; 
         }
         if (temp < 5){
            correct = number - temp;
         }
         else{
            while (number%10!=0){
               number = number + 1;
            }
            correct = number; 
         } 
      }
      first = kb.nextInt();
      if (first == 1){
         System.out.print(name1+", please enter your answer: ");
      }
      else{
         System.out.print(name2+", please enter your answer: ");
      }
      int answer = kb.nextInt();
      //if the person's answer is right, he gets to enter the game first
      if (answer == correct){
         if (first == 1)
            System.out.println("Congratulations. "+name1+" gets the first turn");
         else
            System.out.println("Congratulations. "+name2+" gets the first turn");
      }
      //if the person's answer is wrong, the other person gets to enter the game first
      else{
         if (first == 1){
            System.out.println("Sorry, answer is incorrect. "+name2+" gets the first turn");
            first = 2;
         }
         else{
            System.out.println("Sorry, answer is incorrect. "+name1+" gets the first turn");
            first = 1;
         }
      }
      return first;
   }
   
   public static int forest(int lives,int grade){
      Random random=new Random();
      Scanner kb=new Scanner(System.in);
      int ans,count=0;
      System.out.println("Welcome to forest. There are three questions, ");
      System.out.println("and there is a bonus for you if you answer these questions correctly. ");
      System.out.println("However, if any of three answers is wrong, you will lose the chance to get a bonus.");
      System.out.println("Now, let's begin.");
      int count1=0;
      int count2 = 0;
      if(grade==1){
      /*the questions for grade one students
      if they answer any of the first three questions wrong, they will not get bonus but they get chance to do all three questions*/
         System.out.println("What is the missing number in these sequence?");
         System.out.println("25 30 ? 40 45 Answer: ");
         ans=kb.nextInt();
         if(ans==35){
            System.out.println("Congratulations!"); 
            count2++;
         } 
         else{
            System.out.println("Sorry, right answer is: 35");
         }
         System.out.println("2 4 ? 8 10 12 Answer: ");
         ans=kb.nextInt();
         if(ans==6){
            System.out.println("Congratulations!"); 
            count2++;
         } 
         else{
            System.out.println("Sorry, right answer is: 6");
         }
         System.out.println("1 3 5 7 9 ? Answer: ");
         ans=kb.nextInt();
         if(ans==11){
            System.out.println("Congratulations!"); 
            count2++; 
         }
         else{
            System.out.println("Sorry, right answer is: 11");
         }
      //answer the bonus questions
      //if all three questions are correct, player get chance to get bonus, other wise, no chance 
         if(count2>=3){
            System.out.println("A rainbow appears in the sky and a fairy comes! She will \ntell you the right way to go in the forest");
            System.out.println("You can obtain an extra life, if your answer is right.");
            System.out.println("What is the missing number?");
            System.out.println("3 5 ? 9 11 Answer: ");              
            ans=kb.nextInt();
            if(ans==7){
               System.out.print("Perfect! Go to save the princess. You get a new life");
               lives++;
            }
            else
               System.out.println("Sorry, the right answer is 7,never mind.");
         }
         else if(count2<3){
            //if the player did not meet the fairy girl, he has to go through the forest by himself
            lives=yourself1(lives,count1);
            if(lives==0)
               return lives;
         }
      }
      if(grade==2){
      //questions for grade two students
         for(int i=0;i<3;i++){
            System.out.println();
            int num=random.nextInt(100);
            System.out.print(num+" + ");
            int num1=random.nextInt(100);
            System.out.print(num1+" = ? answer: ");
            ans=kb.nextInt();
         //determine whether the answers are right
            if(ans==num+num1){
               System.out.print("Congratulaitons!");
               count1++;
            }
            //for grade 2, grade 3, and grade 4, if they answer any of the first three questions wrong, they do not keep answering
            else{
               System.out.print("Sorry, right answer is:"+(num+num1)+"\nYou lose the bonus.");
               break;
            }
         }
         //answer the bonus questions, the bonus question is different for different grades
         if(count1>=3){
            System.out.println("A rainbow appears in the sky and a fairy comes! She will \ntell you the right way to go in the forest");
            System.out.println("You can obtain an extra life, if your answer is right.");
            int a=random.nextInt(50);
            int num=random.nextInt(50);
            System.out.print(num+" + "+a+" = ? answer: ");
            ans=kb.nextInt();
            if(ans==(num+a)){
               System.out.print("Perfect! Go to save the princess. You get a new life.");
               lives++;
            }
            else{
               System.out.print("Sorry, the right answer is "+(num+a)+", you should walk out the forest yourself.");
               
            }      
         }
         else if(count1<3){
            System.out.print("Sorry, you lose the chance to gain the bonus, and you \nget lost in the forest.");
            lives=yourself2(lives,count1);   
            if(lives==0)
               return lives;               
         }         
      }
      if(grade==3){
      //questions for grade three students
         for(int i=0;i<3;i++){
            System.out.println();
            int num=random.nextInt(150);
            System.out.print(num+" + ");
            int num1=random.nextInt(150);
            System.out.print(num1+" = ? answer: ");
            ans=kb.nextInt();
         //determine whether the answers are right
            if(ans==num+num1){
               System.out.print("Congratulaitons!");
               count++;
            }
            else{
               System.out.print("Sorry, right answer is:"+(num+num1)+"\nYou lose the bonus.");
               break;
            }
         }
         //answer the bonus questions
         if(count>=3){
            System.out.println("A rainbow appears in the sky and a fairy comes! She will \ntell you the right way to go in the forest");
            System.out.println("You can obtain an extra life, if your answer is right.");
            int a=random.nextInt(100);
            int num=random.nextInt(100);
            System.out.print(num+" + "+a+" = ? answer: ");
            ans=kb.nextInt();
            if(ans==(num+a)){
               System.out.print("Perfect! Go to save the princess.You get a new life.");
               lives++;
            }
            else{
               System.out.print("Sorry, the right answer is "+(num+a)+", you should walk out the forest yourself.");
               
            }
         }
         else if(count1<3){
            System.out.print("Sorry, you lose the chance to gain the bonus, and you \nget lost in the forest.");                  
            lives=yourself3(lives,count1);
            if(lives==0)
               return lives;
         }         
      }
      if(grade==4){
      //questions for grade four students
         for(int i=0;i<3;i++){
            System.out.println();
            int num=random.nextInt(300);
            System.out.print(num+" + ");
            int num1=random.nextInt(300);
            System.out.print(num1+" = ? answer: ");
            ans=kb.nextInt();
         //determine whether the answers are right
            if(ans==num+num1){
               System.out.print("Congratulaitons!");
               count++;
            }
            else{
               System.out.print("Sorry, right answer is:"+(num+num1)+"\nYou lose the bonus.");
               break;
            }
         }
         //answer the bonus questions
         if(count1>=3){
            System.out.println("A rainbow appears in the sky and a fairy comes! She will \ntell you the right way to go in the forest");
            System.out.println("You can obtain an extra life, if your answer is right.");
            int a=random.nextInt(200);
            int num=random.nextInt(200);
            System.out.print(num+" + "+a+" = ? answer: ");
            ans=kb.nextInt();
            if(ans==(num+a)){
               System.out.print("Perfect! Go to save the princess. You get a new life.");
               lives++;
            }
            else{
               System.out.print("Sorry, the right answer is "+(num+a)+" you should walk out the forest yourself.");
               
            }
         }
         else if(count1<3){
            System.out.print("Sorry, you lose the chance to gain the bonus, and you \nget lost in the forest.");
            lives=yourself4(lives,count1);
            if(lives == 0)
               return lives;                  
         }         
      }
      return lives;
   } 
  
   //this method is for player cross the forest by himself(grade1)  
   //when player is crossing the forest by himself, he will lose one life if he answers one question wrong , until he has no life left               
   public static int yourself1(int lives,int count1){  
      Scanner kb=new Scanner(System.in);
      Random random=new Random();
      //five more questions, player has to answer at least two questions right in order to go through the forest, otherwise, fail and game over
      for(int j=0;j<5;j++){
         System.out.println();
         int num3=random.nextInt(10);
         System.out.print(num3+" + ");
         int num4=random.nextInt(10);
         System.out.print(num4+" + ");
         int num5=random.nextInt(10);
         System.out.print(num5+" = ? Answer: ");
         int ans=kb.nextInt();
         count1++;
         if(ans==num3+num4+num5){
            System.out.println("Congratulations!");
         }
         else{
            if(lives==1){
               System.out.print("Sorry, mission fail.");
               lives = lives - 1;
               return lives;
            }
            System.out.println("Sorry, the right answer is: "+(num3+num4+num5));
            lives--;
         } 
      }
      return lives;
   }  
   
   //grade 2
   public static int yourself2(int lives,int count1){  
      Scanner kb=new Scanner(System.in);
      Random random=new Random();
      //five more questions
      for(int j=0;j<5;j++){
         System.out.println();
         int num3=random.nextInt(100);
         System.out.print(num3+" + ");
         int num4=random.nextInt(100);
         System.out.print(num4+" + ");
         int num5=random.nextInt(100);
         System.out.print(num5+" = ? Answer: ");
         int ans=kb.nextInt();
         count1++;
         if(ans==num3+num4+num5){
            System.out.println("Congratulations!");
         }
         else{
            if(lives==1){
               System.out.print("Sorry, mission fail.");
               lives = lives - 1;
               return lives;
            }
            lives = lives -1;
            System.out.println("Sorry, the right answer is: "+(num3+num4+num5));
         }
       
      }
      return lives;
   }
   
   
   
   //grade 3
   public static int yourself3(int lives,int count1){  
      Scanner kb=new Scanner(System.in);
      Random random=new Random();
      //five more questions
      for(int j=0;j<5;j++){
         System.out.println();
         int num3=random.nextInt(150);
         System.out.print(num3+" + ");
         int num4=random.nextInt(150);
         System.out.print(num4+" + ");
         int num5=random.nextInt(150);
         System.out.print(num5+" = ? Answer: ");
         int ans=kb.nextInt();
         count1++;
         if(ans==num3+num4+num5){
            System.out.println("Congratulations!");
         }
         else{
            if(lives==1){
               System.out.print("Sorry, mission fail.");
               lives = lives - 1;
               return lives;
            }
         
            System.out.println("Sorry, the right answer is: "+(num3+num4+num5));
            lives = lives - 1;
         } 
      
      } 
      return lives;
   }
   
   //grade4 
   public static int yourself4(int lives,int count1){  
      Scanner kb=new Scanner(System.in);
      Random random=new Random();
      //five more questions
      for(int j=0;j<5;j++){
         System.out.println();
         int num3=random.nextInt(1000);
         System.out.print(num3+" + ");
         int num4=random.nextInt(1000);
         System.out.print(num4+" + ");
         int num5=random.nextInt(1000);
         System.out.print(num5+" = ? Answer: ");
         int ans=kb.nextInt();
         count1++;
         if(ans==num3+num4+num5){
            System.out.println("Congratulations!");
         }
         else{
            if(lives==1){
               System.out.print("Sorry, mission fail.");
               lives = lives - 1;
               return lives;
            }
            System.out.println("Sorry, the right answer is: "+(num3+num4+num5));
            lives = lives - 1;
         } 
      
      }
      return lives;
   }
  

   //for different grades, numbers will be different within different ranges
   public static int Q1(int grade, int lives) {
      Scanner kb = new Scanner(System.in);
      Random random = new Random();
      int n1 = 0, n2 = 0, n3 = 0, n4 = 0, g1 = 0;
   
      if (grade == 1) {
         n1 = random.nextInt(10) + 2;
         n2 = random.nextInt(10) + 2;
         n3 = random.nextInt(10) + 2;
         n4 = random.nextInt(10) + 50;
         System.out.println("You go to the store and buy two candies($" + n1 + "), ice cream ($" + n2 + "), and a toy($" + n3 + ").");
         System.out.print(" If you give the cashier " + n4 + " what change would you get back? ");
      } else if (grade == 2) {
         n1 = random.nextInt(50) + 150;
         n2 = random.nextInt(50) + 80;
         n3 = random.nextInt(50) + 20;
         n4 = random.nextInt(50) + 500;
         System.out.println("You go to the store and buy a table($" + n1 + "), a chair ($" + n2 + "), and a lamp($" + n3 + ").");
         System.out.print(" If you give the cashier " + n4 + " what change would you get back? ");
      } else if (grade == 3) {
         n1 = random.nextInt(100) + 480;
         n2 = random.nextInt(100) + 260;
         n3 = random.nextInt(100) + 640;
         n4 = random.nextInt(100) + 2000;
         System.out.println("You go to the store and buy a bed($" + n1 + "), two chairs ($" + n2 + "), and a television($" + n3 + ").");
         System.out.print(" If you give the cashier " + n4 + " what change would you get back? ");
      } else if (grade == 4) {
         n1 = random.nextInt(500) + 4000;
         n2 = random.nextInt(500) + 2000;
         n3 = random.nextInt(500) + 6000;
         n4 = random.nextInt(500) + 20000;
         System.out.println("You go to the store and buy a sofa($" + n1 + "), two televisions($" + n2 + "), and an air-conditional($" + n3 + ").");
         System.out.print(" If you give the cashier " + n4 + " what change would you get back? ");
      }
      g1 = kb.nextInt();
        //if the answer is correct, the player gets a key
      if (g1 == n4-n1-n2-n3) {
         System.out.println("One guard is conquered! You get a key!");
      } else {
           //if the answer is incorrect, the player lose one life
         if (lives >= 2) {
            lives = lives-1;
            System.out.println("Oh no! You have " + lives + " life left.");
         } else if (lives == 1) {
              //if the player has no life left, game ends
            lives = lives - 1;
            System.out.println("You have failed! See you next time!");
            System.out.println("Game over");
         }
      }
      return lives;
   }
   
   //this method is for the second demon
   public static int Q2 (int grade, int lives) {
      Scanner kb = new Scanner (System.in);
      Random random = new Random();
      int n1 = 0, n2 = 0, n3 = 0, n4 = 0, g2 = 0;
      if (grade == 1) {
         n1 = random.nextInt(10) + 32;
         n2 = random.nextInt(10) + 22;
         n3 = random.nextInt(10) + 2;
         n4 = random.nextInt(10) + 2;
         System.out.println("There are " + n1 + " red fish and " + n2 + " blue fish in the river. The fisherman catches " + n3 + " blue fish,");
         System.out.print(" then puts " + n4 + " yellow fish into the river. How many fish are there in the river? ");
      } else if (grade == 2) {
         n1 = random.nextInt(50) + 150;
         n2 = random.nextInt(50) + 150;
         n3 = random.nextInt(50) + 60;
         n4 = random.nextInt(50) + 40;
         System.out.println("There are " + n1 + " red fish and " + n2 + " blue fish in the river. The fisherman catches " + n3 + " blue fish,");
         System.out.print(" then puts " + n4 + " yellow fish into the river. How many fish are there in the river? ");
      } else if (grade == 3) {
         n1 = random.nextInt(100) + 1000;
         n2 = random.nextInt(100) + 1000;
         n3 = random.nextInt(100) + 640;
         n4 = random.nextInt(100) + 480;
         System.out.println("There are " + n1 + " red fish and " + n2 + " blue fish in the river. The fisherman catches " + n3 + " blue fish,");
         System.out.print(" then puts " + n4 + " yellow fish into the river. How many fish are there in the river? ");
      } else if (grade == 4) {
         n1 = random.nextInt(500) + 10000;
         n2 = random.nextInt(500) + 10000;
         n3 = random.nextInt(500) + 6000;
         n4 = random.nextInt(500) + 8000;
         System.out.println("There are " + n1 + " red fish and " + n2 + " blue fish in the river. The fisherman catches " + n3 + " blue fish,");
         System.out.print(" then puts " + n4 + " yellow fish into the river. How many fish are there in the river? ");
      }
      g2 = kb.nextInt();
      if (g2 == n1+n2-n3+n4) {
         System.out.println("One guard is conquered! You get a key!");
      } else {
         if (lives >= 2) {
            lives = lives-1;
            System.out.println("Oh no! You have " + lives + " life left.");
         } else if (lives == 1) {
            lives = lives - 1;
            System.out.println("Sorry, You not have life");
            System.out.println("You have failed! See you next time!");
            System.out.println("Game over");
         }
      }
   
      return lives;
   }
   
   //this method is for the third demon
   public static int Q3(int grade, int lives) {
      Scanner kb = new Scanner (System.in);
      Random random = new Random();
      int n1 = 0, n2 = 0, n3 = 0, g3 = 0;
      int correct = 0;
      //for grade 1 and grade 2 , give them addition and subtraction problems
      if (grade == 1) {
         n1 = random.nextInt(10) + 10;
         n2 = random.nextInt(10) + 2;
         n3 = random.nextInt(10) + 50;
         System.out.println(n1 + " + " + n2 + " = " + n3 + " - " + " ?");
         correct = n3-n1-n2;
         System.out.print("Which number makes the equation true? ");
      } else if (grade == 2) {
         n1 = random.nextInt(50) + 150;
         n2 = random.nextInt(50) + 80;
         n3 = random.nextInt(50) + 400;
         System.out.println(n1 + " + " + n2 + " = " + n3 + " - " + " ?");
         correct = n3 - n1 - n2;
         System.out.print("Which number makes the equation true? ");
      } else if (grade == 3) {
         //for grade 3 and grade 4, test them on multiplicity also
         n1 = random.nextInt(10) + 5;
         n2 = random.nextInt(10) + 2;
         n3 = random.nextInt(10);
         System.out.println(n1 + " x " + n2 + " = " + n3 + " + " + " ?");
         correct = n1*n2-n3;
         System.out.print("Which number makes the equation true? ");
      } else if (grade == 4) {
         n1 = random.nextInt(10) + 25;
         n2 = random.nextInt(10) + 2;
         n3 = random.nextInt(50);
         System.out.println(n1 + " x " + n2 + " = " + n3 + " + " + " ?");
         correct = n1*n2-n3;
         System.out.print("Which number makes the equation true? ");
      }
      g3 = kb.nextInt();
      if (g3 == correct) {
         System.out.println("One guard is conquered! You get a key!");
      } else {
         if (lives >= 2) {
            lives = lives-1;
            System.out.println("Oh no! You have " + lives + " life left.");
         } else if (lives == 1) {
            lives = lives - 1;
            System.out.println("Sorry, You not have life");
            System.out.println("You have failed! See you next time!");
            System.out.println("Game over");
         }
      }
   
      return lives;
   }
   
   //this method is for the fourth demon
   public static int Q4(int grade, int lives) {
      Scanner kb = new Scanner (System.in);
      Random random = new Random();
      int n1 = 0, n2 = 0, n3 = 0, n4 = 0, g4 = 0;
      int correct = 0;
      if (grade == 1) {
         n1 = random.nextInt(10) + 50;
         n2 = random.nextInt(10) + 20;
         n3 = random.nextInt(10) + 10;
         n4 = random.nextInt(10);
         System.out.println(n1 + " + " + n2 + " - " + n3 + " = " + n4 + " + " + " ?");
         System.out.print("Which number makes the equation true? ");
         correct = n1+n2-n3-n4;
         
      } else if (grade == 2) {
         n1 = random.nextInt(50) + 400;
         n2 = random.nextInt(50) + 80;
         n3 = random.nextInt(50) + 150;
         n4 = random.nextInt(100);
         System.out.println(n1 + " + " + n2 + " - " + n3 + " = " + n4 + " + " + " ?");
         System.out.print("Which number makes the equation true? ");
         correct = n1+n2-n3-n4;
      } else if (grade == 3) {
         n1 = random.nextInt(10) + 50;
         n2 = random.nextInt(10) + 1;
         n3 = random.nextInt(10) + 1;
         n4 = random.nextInt(30);
         System.out.println(n1 + " + " + n2 + " x " + n3 + " = " + n4 + " + " + " ?");
         System.out.print("Which number makes the equation true? ");
         correct = n1+(n2*n3)-n4;
      } else if (grade == 4) {
         n1 = random.nextInt(10) + 500;
         n2 = random.nextInt(10) + 10;
         n3 = random.nextInt(10) + 1;
         n4 = random.nextInt(200);
         System.out.println(n1 + " + " + n2 + " x " + n3 + " = " + n4 + " + " + " ?");
         System.out.print("Which number makes the equation true? ");
         correct = n1+(n2*n3)-n4;
      }
      g4 = kb.nextInt();
      if (g4 == correct) {
         System.out.println("One guard is conquered! You get a key!");
      } else {
         if (lives >= 2) {
            lives = lives-1;
            System.out.println("Oh no! You have " + lives + " life left.");
         } else if (lives == 1) {
            lives = lives - 1;
            System.out.println("Sorry, You not have life");
            System.out.println("You have failed! See you next time!");
         }
      }
   
      return lives;
   }
   
   //this method is for the fifth demon, for this part, give true or false problems
   public static int Q5(int grade, int lives) {
      Scanner kb = new Scanner (System.in);
      Random random = new Random();
      int n1 = 0, n2 = 0, n3 = 0;
      boolean g5 = true, correct = true ;
      if (grade == 1) {
         n1 = random.nextInt(10) + 50;
         n2 = random.nextInt(50);
         n3 = random.nextInt(50);
         System.out.print("Is " + n1 + " subtracts " + n2 + " equals to " + n3 + "(true or false?)");
         g5 = kb.nextBoolean();
         if (n3 == n1-n2) {
            correct = true;
         } else {
            correct = false;
         }
      } else if (grade == 2) {
         n1 = random.nextInt(100) + 100;
         n2 = random.nextInt(100) + 100;
         n3 = random.nextInt(100) + 400;
         System.out.print("Is " + n1 + " add " + n2 + " equals to " + n3 + "(true or false?)");
         g5 = kb.nextBoolean();
         if (n3 == n1+n2) {
            correct = true;
         } else {
            correct = false;
         }
         
      } else if (grade == 3) {
         n1 = random.nextInt(10) + 1;
         n2 = random.nextInt(10);
         n3 = random.nextInt(100);
         System.out.print("Is " + n1 + " multiply " + n2 + " equals to " + n3 + "(true or false?)");
         g5 = kb.nextBoolean();
         if (n3 == n1*n2) {
            correct = true;
         } else {
            correct = false;
         }
      } else if (grade == 4) {
         n1 = random.nextInt(90) + 10;
         n2 = random.nextInt(10) + 1;
         n3 = random.nextInt(1000);
         System.out.print("Is " + n1 + " multiply " + n2 + " equals to " + n3 + "(true or false?)");
         g5 = kb.nextBoolean();
         if (n3 == n1*n2) {
            correct = true;
         } else {
            correct = false;
         }
      }
      if (g5 == correct) {
         System.out.println("One guard is conquered! You get a key!");
      } else {
         if (lives >= 2) {
            lives = lives-1;
            System.out.println("Oh no! You have " + lives + " life left.");
         } else if (lives == 1) {
            System.out.println("Sorry, You not have life");
            System.out.println("You have failed! See you next time!");
         }
      }
   
      return lives;
   }
   
   //this is the first question when fighting with the last beast boss, for this part, give multiple choices quesions
   public static int Q6(int grade, int lives) {
      Scanner kb = new Scanner (System.in);
      char correct = ' ';
      char g6 = ' ';
      if (grade == 1) {
         System.out.println("What number has 8 ten and 9 ones?");
         System.out.println("a. 89     b. 98    c.78     d.87");
         System.out.print("Your answer is ");
         g6 = kb.next().charAt(0);
         correct = 'a';
      } else if (grade == 2) {
         System.out.println("What number has 3 hundreds, 2 ten and 9 ones?");
         System.out.println("a. 239     b. 329    c.932     d.392");
         System.out.print("Your answer is ");
         g6 = kb.next().charAt(0);
         correct = 'b';
      } else if (grade == 3) {
         System.out.println("What number has 5 hundreds, 12 ten and 6 ones?");
         System.out.println("a. 512     b. 526    c.626     d.622");
         System.out.print("Your answer is ");
         g6 = kb.next().charAt(0);
         correct = 'c';
      } else if (grade == 4) {
         System.out.println("What number has 30 hundreds, 23 ten and 9 ones?");
         System.out.println("a. 3239     b. 3023    c.3039     d.3932");
         System.out.print("Your answer is ");
         g6 = kb.next().charAt(0);
         correct = 'a';
      }
      if (g6 != correct) {
         if (lives >= 2) {
            lives = lives-1;
            System.out.println("Oh no! You have " + lives + " life left.");
         } else if (lives == 1) {
            System.out.println("Sorry, You not have life");
            System.out.println("You have failed! See you next time!");
            return lives;
         }
      }
      return lives;
   }
   
   //this is the second question
   public static int Q7(int grade, int lives) {
      Scanner kb = new Scanner (System.in);
      char correct = ' ';
      char g7 = ' ';
      if (grade == 1) {
         System.out.println("What do you make 6?");
         System.out.println("a. 8-3     b. 10-4    c. 4+3   d. 4+4 ");
         System.out.print("Your answer is ");
         g7 = kb.next().charAt(0);
         correct = 'b';
      } else if (grade == 2) {
         System.out.println("What do you make 23?");
         System.out.println("a. 96-73     b. 96-72    c. 95-73    d. 93-76");
         System.out.print("Your answer is ");
         g7 = kb.next().charAt(0);
         correct = 'a';
      } else if (grade == 3) {
         System.out.println("What do you make 12?");
         System.out.println("a. 3x5     b. 2x6    c.2x8     d.3x3");
         System.out.print("Your answer is ");
         g7 = kb.next().charAt(0);
         correct = 'b';
      } else if (grade == 4) {
         System.out.println("What do you make 252?");
         System.out.println("a. 25x3     b.22x5     c.81x2     d.42x6");
         System.out.print("Your answer is ");
         g7 = kb.next().charAt(0);
         correct = 'd';
      }
      if (g7 != correct) {
         if (lives >= 2) {
            lives = lives-1;
            System.out.println("Oh no! You have " + lives + " life left.");
         } else if (lives == 1) {
            System.out.println("Sorry, You not have life");
            System.out.println("You have failed! See you next time!");
            return lives;
         }
      }
   
      return lives;
   }
   
   //this method is for the third quesion
   public static int Q8(int grade, int lives) {
      Scanner kb = new Scanner (System.in);
      char g8 = ' ';
      char correct = ' ';
      if (grade == 1) {
         System.out.println("If 12 subtracts a number is 8, what is the number it is?");
         System.out.println("a. 6     b. 4    c.5     d.3");
         System.out.print("Your answer is ");
         g8 = kb.next().charAt(0);
         correct = 'b';
      } else if (grade == 2) {
         System.out.println("If 320 add a number is 896, what is the number it is?");
         System.out.println("a. 676      b. 666    c. 576     d. 577");
         System.out.print("Your answer is ");
         g8 = kb.next().charAt(0);
         correct = 'c';
      } else if (grade == 3) {
         System.out.println("If half of a number is 30, what is the number it is?");
         System.out.println("a. 15    b.32     c. 60     d. 61");
         System.out.print("Your answer is ");
         g8 = kb.next().charAt(0);
         correct = 'c';
      } else if (grade == 4) {
         System.out.println("If half of a number is 64, what is the number it is?");
         System.out.println("a. 65      b.32     c. 78     d. 128");
         System.out.print("Your answer is ");
         g8 = kb.next().charAt(0);
         correct = 'd';
      }
      if (g8 != correct) {
         if (lives >= 2) {
            lives = lives-1;
            System.out.println("Oh no! You have " + lives + " life left.");
         } else if (lives == 1) {
            System.out.println("Sorry, You not have life");
            System.out.println("You have failed! See you next time!");
            return lives;
         }
      }
   
      return lives;
   }
   
   //this is for the fourth quesion
   public static int Q9(int grade, int lives) {
      Scanner kb = new Scanner (System.in);
      char g9 = ' ';
      char correct = ' ';
      if (grade == 1) {
         System.out.println("The sum of which answer below is 30?");
         System.out.println("a.10,20      b.16,34     c.23,6      d.12,17");
         System.out.print("Your answer is ");
         g9 = kb.next().charAt(0);
         correct = 'a';
      } else if (grade == 2) {
         System.out.println("The sum of which answer below is 864?");
         System.out.println("a. 233,631      b. 233,671     c. 239,631      d. 239,671");
         System.out.print("Your answer is ");
         g9 = kb.next().charAt(0);
         correct = 'a';
      } else if (grade == 3) {
         System.out.println("The product of which answer below is 16?");
         System.out.println("a. 4x4     b. 3x4    c. 10x6     d. 1x6");
         System.out.print("Your answer is ");
         g9 = kb.next().charAt(0);
         correct = 'a';
      } else if (grade == 4) {
         System.out.println("The product of which answer below is 125?");
         System.out.println("a. 15x5     b. 25x5    c. 120x5     d. 25x12");
         System.out.print("Your answer is ");
         g9 = kb.next().charAt(0);
         correct = 'b';
      }
      if (g9 != correct) {
         if (lives >= 2) {
            lives = lives-1;
            System.out.println("Oh no! You have " + lives + " life left.");
         } else if (lives == 1) {
            System.out.println("Sorry, You not have life");
            System.out.println("You have failed! See you next time!");
            return lives;
         }
      }
   
      return lives;
   }
   
   //this is for the fifth quesion
   public static int Q10(int grade, int lives) {
      Scanner kb = new Scanner (System.in);
      char g10 = ' ';
      char correct = ' ';
      if (grade == 1) {
         System.out.println("Calculate the the result of the sum of 3 and 12.");
         System.out.println("a. 26     b. 16    c. 15     d. 9");
         System.out.print("Your answer is ");
         g10 = kb.next().charAt(0);
         correct = 'c';
      } else if (grade == 2) {
         System.out.println("Calculate the the result of the sum of 348 and 126.");
         System.out.println("a. 460     b. 474    c. 473     d. 126");
         System.out.print("Your answer is ");
         g10 = kb.next().charAt(0);
         correct = 'b';
      } else if (grade == 3) {
         System.out.println("Calculate the the result of the product of 3 and 12.");
         System.out.println("a. 23     b. 34    c. 28    d. 36");
         System.out.print("Your answer is ");
         g10 = kb.next().charAt(0);
         correct = 'd';
      } else if (grade == 4) {
         System.out.println("Calculate the the result of the product of 36 and 9.");
         System.out.println("a. 324     b. 314    c. 340     d. 410");
         System.out.print("Your answer is ");
         g10 = kb.next().charAt(0);
         correct = 'a';
      }
      if (g10 != correct) {
         if (lives >= 2) {
            lives = lives-1;
            System.out.println("Oh no! You have " + lives + " life left.");
         } else if (lives == 1) {
            System.out.println("Sorry, You not have life");
            System.out.println("You have failed! See you next time!");
            return lives;
         }
      }
   
      return lives;
   }
   
   //this is the last quesion
   public static int Q11(int grade, int lives) {
      Scanner kb = new Scanner (System.in);
      char g11 = ' ';
      char correct = ' ';
      if (grade == 1) {
         System.out.println("Which equations can be calculated to 45?");
         System.out.println("a. 60-25    b. 20+15    c. 50-15     d. 22+23");
         System.out.print("Your answer is ");
         g11 = kb.next().charAt(0);
         correct = 'd';
      } else if (grade == 2) {
         System.out.println("Which equations can be calculated to 245?");
         System.out.println("a. 89+147     b. 98+147    c. 328-78     d. 328-87");
         System.out.print("Your answer is ");
         g11 = kb.next().charAt(0);
         correct = 'b';
      } else if (grade == 3) {
         System.out.println("Which equations can be calculated to 45?");
         System.out.println("a. 23+21     b. 5x9     c. 56-12    d. 9x6");
         System.out.print("Your answer is ");
         g11 = kb.next().charAt(0);
         correct = 'b';
      } else if (grade == 4) {
         System.out.println("Which equations can be calculated to 225?");
         System.out.println("a. 500-250     b. 25x5    c. 5x45     d. 87+65");
         System.out.print("Your answer is ");
         g11 = kb.next().charAt(0);
         correct = 'c';
      }
      if (g11 != correct) {
         if (lives >= 2) {
            lives = lives-1;
            System.out.println("Oh no! You have " + lives + " life left.");
         } else if (lives == 1) {
            System.out.println("Sorry, You not have life");
            System.out.println("You have failed! See you next time!");
            return lives;
         }
      }
   
      return lives;
   }
   
   //this method is for the whole part of fighting against five demons
   public static int FiveDemon(int grade,int lives,int key){
      int temp;
      temp = lives;
      lives = Q1(grade,lives);
      if(lives != 0){
         if(lives == temp){
            key = key + 1;
         }
         temp = lives;
         lives = Q2(grade,lives);
         if(lives != 0){
            if(lives == temp){
               key = key + 1;
            }
            temp = lives;
            lives = Q3(grade,lives);
            if (lives!=0){
               if(lives == temp){
                  key = key + 1;
               }
               temp = lives;
               lives = Q4(grade,lives);
               if(lives != 0){
                  if(lives == temp){
                     key = key + 1;
                  }
                  temp = lives;
                  lives = Q5(grade,lives);
                  if(lives!=0){
                     if(lives == temp){
                        key = key + 1;
                     }
                  } 
               }
            }
         }
      }
      return key;
   }
   
   //this method is for fighting with the beast boss
   public static int boss(int grade,int lives){
      lives = Q6(grade,lives);
      if(lives!=0){
         lives = Q7(grade,lives);
         if(lives!=0){
            lives = Q8(grade,lives);
            if(lives!=0){
               lives = Q9(grade,lives);
               if(lives!=0){
                  lives = Q10(grade,lives);
                  if(lives!=0){
                     lives = Q11(grade,lives);
                  }
               }
            }
         }
      }
      return lives;
   }
}

   



   
   
