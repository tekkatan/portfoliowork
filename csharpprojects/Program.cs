/*
Author: Tanja Bekker
*/

/*
- A simple console program game where a random dice controls the behavior of a cat
  and their energy level.
*/
int catEnergyLevel = 10;
Random dice = new Random();
string[] catBehavior = {"zoomies.",
 "purrs Loudly.",
 "miauws loudly for no reason at the lamp.",
    "purrs while eating catfood.",
    "speaking bribberish while redecorating the house with catlitter.",
    "making biscuits on your lap." };

do
{
    int roll = dice.Next(1, 7);
    // 1. Check roll dice value
    switch (roll)
    {
        /* 2. Assign the corresponding outcome as text on the console as well as an increase or decrease of the 
              catEnergyLevel
        */
        case 1:
            Console.WriteLine($"The cat has {catBehavior[0]}");
            catEnergyLevel--;
            break;
        case 2:
            Console.WriteLine($"The cat {catBehavior[1]}");
            catEnergyLevel++;
            break;
        case 3:
            Console.WriteLine($"The cat {catBehavior[2]}");
            catEnergyLevel--;
            break;
        case 4:
            Console.WriteLine($"The cat is {catBehavior[3]}");
            catEnergyLevel++;
            break;
        case 5:
            Console.WriteLine($"The cat is {catBehavior[4]}");
            catEnergyLevel--;
            break;
        case 6:
            Console.WriteLine($"The cat is {catBehavior[5]}");
            catEnergyLevel++;
            break;

    }   
}while (catEnergyLevel >= 1) ;
// 3. Printing a message when the health of the cat is not bigger than or equal to 1 anymore.
Console.WriteLine("The cat is tired now, please come back later to play."); 
