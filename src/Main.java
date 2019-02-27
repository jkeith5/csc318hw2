import java.util.ArrayList;

public class Main {
    static int harvested = 0;
    static int harvested1=0;
    static int harvested2=0;
    static int averageTreesSeeded[]= new int[4];
    static int averageTreesThree[]= new int[4];
    static int averageTreesThreeFour[]= new int[4];//these three track amount of acres per age group
    static ArrayList<Integer> harvestedList= new ArrayList<>();
    static ArrayList<Integer> harvestedList1= new ArrayList<>();
    static ArrayList<Integer> harvestedList2= new ArrayList<>();

    public static void main(String[] args) {
        double rainfallChance = 0;

        double fireChance, fireChance1, fireChance2 = 0;
        AcresTrees sprayThree[] = new AcresTrees[4];//spray three year old trees
        sprayThree[0] = new AcresTrees(400000, 0, 1);//first year
        sprayThree[1] = new AcresTrees(300000, 0, 1);//second year
        sprayThree[2] = new AcresTrees(200000, 0, 1);//third year
        sprayThree[3] = new AcresTrees(100000, 0, 1);//fourth year
        AcresTrees seeded[] = new AcresTrees[4];//clouds that are seeded test
        seeded[0] = new AcresTrees(400000, 1, 0);
        seeded[1] = new AcresTrees(300000, 1, 0);
        seeded[2] = new AcresTrees(200000, 1, 0);
        seeded[3] = new AcresTrees(100000, 1, 0);
        AcresTrees sprayThreeFour[] = new AcresTrees[4];//spray half of the 3 and 4 year old trees.
        sprayThreeFour[0] = new AcresTrees(400000, 0, 2);
        sprayThreeFour[1] = new AcresTrees(300000, 0, 2);
        sprayThreeFour[2] = new AcresTrees(200000, 0, 2);
        sprayThreeFour[3] = new AcresTrees(100000, 0, 2);
        for (int i = 0; i < 100; i++) {
            rainfallChance = determineRainfall();
            for (int j = 0; j < 4; j++) {//determines rainfall for the year for both seeded and non seeded
                sprayThree[j].rainfallIn = sprayThree[j].determineRainAmount(rainfallChance);
                sprayThreeFour[j].rainfallIn = sprayThreeFour[j].determineRainAmount(rainfallChance);
                seeded[j].rainfallIn = seeded[j].determineRainAmount(rainfallChance);
                averageTreesSeeded[j]+=seeded[j].amountofAcres;
                averageTreesThree[j]+=sprayThree[j].amountofAcres;
                averageTreesThreeFour[j]+=sprayThreeFour[j].amountofAcres;
            }
            fireChance = determineFire(sprayThree[0].rainfallIn);
            fireChance1 = determineFire(sprayThreeFour[0].rainfallIn);
            fireChance2 = determineFire(seeded[0].rainfallIn);
            if (fireChance > Math.random()) {//fire happens if random is lower
                if (sprayThree[0].rainfallIn > 0 && sprayThree[0].rainfallIn < 4) {//drought
                    sprayThree[0].amountofAcres *= .85;
                    sprayThree[1].amountofAcres *= .82;
                    sprayThree[2].amountofAcres *= .78;
                    sprayThree[3].amountofAcres *= .70;
                } else if (sprayThree[0].rainfallIn > 3 && sprayThree[0].rainfallIn < 11) {//moderate rain
                    sprayThree[0].amountofAcres *= .90;
                    sprayThree[1].amountofAcres *= .88;
                    sprayThree[2].amountofAcres *= .85;
                    sprayThree[3].amountofAcres *= .80;
                } else {//heavy rain
                    sprayThree[0].amountofAcres *= .95;
                    sprayThree[1].amountofAcres *= .93;
                    sprayThree[2].amountofAcres *= .90;
                    sprayThree[3].amountofAcres *= .85;
                }
            }
            if (fireChance1 > Math.random()) {
                if (sprayThreeFour[0].rainfallIn > 0 && sprayThreeFour[0].rainfallIn < 4) {//drought
                    sprayThreeFour[0].amountofAcres *= .85;
                    sprayThreeFour[1].amountofAcres *= .82;
                    sprayThreeFour[2].amountofAcres *= .78;
                    sprayThreeFour[3].amountofAcres *= .70;
                } else if (sprayThreeFour[0].rainfallIn > 3 && sprayThreeFour[0].rainfallIn < 11) {//moderate rain
                    sprayThreeFour[0].amountofAcres *= .90;
                    sprayThreeFour[1].amountofAcres *= .88;
                    sprayThreeFour[2].amountofAcres *= .85;
                    sprayThreeFour[3].amountofAcres *= .80;
                } else {//heavy rain
                    sprayThreeFour[0].amountofAcres *= .95;
                    sprayThreeFour[1].amountofAcres *= .93;
                    sprayThreeFour[2].amountofAcres *= .90;
                    sprayThreeFour[3].amountofAcres *= .85;
                }
            }
            if (fireChance2 > Math.random()) {
                if (seeded[0].rainfallIn > 0 && seeded[0].rainfallIn < 4) {//drought
                    seeded[0].amountofAcres *= .85;
                    seeded[1].amountofAcres *= .82;
                    seeded[2].amountofAcres *= .78;
                    seeded[3].amountofAcres *= .70;
                } else if (seeded[0].rainfallIn > 3 && seeded[0].rainfallIn < 11) {//moderate rain
                    seeded[0].amountofAcres *= .90;
                    seeded[1].amountofAcres *= .88;
                    seeded[2].amountofAcres *= .85;
                    seeded[3].amountofAcres *= .80;
                } else {//heavy rain
                    seeded[0].amountofAcres *= .95;
                    seeded[1].amountofAcres *= .93;
                    seeded[2].amountofAcres *= .90;
                    seeded[3].amountofAcres *= .85;
                }
            }
            //end of fire deaths
            //beetle deaths and deaths from lack of water
            beetleAndDehydroDeaths(seeded);
            beetleAndDehydroDeaths(sprayThree);
            beetleAndDehydroDeaths(sprayThreeFour);//end of tree deaths
            //calc trees that grow and repopulate after growing
            calcGrowth(seeded, seeded[0].sprayType);
            calcGrowth(sprayThree, sprayThree[0].sprayType);
            calcGrowth(sprayThreeFour, sprayThreeFour[0].sprayType);


        }//end of sim
        System.out.println("Simulation ran for 100 years, Wood Cutting Sim.");
        System.out.println("\t\t\t\t First Policy: Seeding Clouds\t\t\t\t\t\t\t\t 1 Year\t\t\t2 Year\t\t\t 3 Year\t\t\t4 Year");
        System.out.printf("\tAverage Harvested: %f \t\t Average Trees by Age Group: %f\t%f\t%f\t%f" +
                        "\n\tVariance Harvested: %f \t\t Variance Trees by Age Group: %f\t%f\t%f\t%f\n",((double)harvested/100),((double)averageTreesSeeded[0]/100),
                ((double)averageTreesSeeded[1]/100),((double)averageTreesSeeded[2]/100),((double)averageTreesSeeded[3]/100),
                calcVariance((harvested/100),harvestedList),
                calcVariance((double)(averageTreesSeeded[0]/100), seeded[0].averageTrees),
                calcVariance((double)(averageTreesSeeded[1]/100), seeded[1].averageTrees),
                calcVariance((double)(averageTreesSeeded[2]/100), seeded[2].averageTrees),
                calcVariance((double)(averageTreesSeeded[3]/100), seeded[3].averageTrees));
        System.out.println("\t\t\t\t Second Policy: Spraying 3YOs\t\t\t\t\t\t\t\t 1 Year\t\t\t2 Year\t\t\t 3 Year\t\t\t4 Year");
        System.out.printf("\tAverage Harvested: %f \t\t Average Trees by Age Group: %f\t%f\t%f\t%f" +
                        "\n\tVariance Harvested: %f \t\t Variance Trees by Age Group: %f\t%f\t%f\t%f\n",((double)harvested1/100),((double)averageTreesThree[0]/100),
                ((double)averageTreesThree[1]/100),((double)averageTreesThree[2]/100),((double)averageTreesThree[3]/100),
                calcVariance((harvested/100),harvestedList),
                calcVariance((double)(averageTreesThree[0]/100), sprayThree[0].averageTrees),
                calcVariance((double)(averageTreesThree[1]/100), sprayThree[1].averageTrees),
                calcVariance((double)(averageTreesThree[2]/100), sprayThree[2].averageTrees),
                calcVariance((double)(averageTreesThree[3]/100), sprayThree[3].averageTrees));
        System.out.println("\t\t\t\t Third Policy: Spray 50% of 3YOs and 4YOs\t\t\t\t\t 1 Year\t\t\t2 Year\t\t\t 3 Year\t\t\t4 Year");
        System.out.printf("\tAverage Harvested: %f \t\t Average Trees by Age Group: %f\t%f\t%f\t%f" +
                        "\n\tVariance Harvested: %f \t\t Variance Trees by Age Group: %f\t%f\t%f\t%f\n",((double)harvested1/100),((double)averageTreesThreeFour[0]/100),
                ((double)averageTreesThreeFour[1]/100),((double)averageTreesThreeFour[2]/100),((double)averageTreesThreeFour[3]/100),
                calcVariance((harvested/100),harvestedList),
                calcVariance((double)(averageTreesThreeFour[0]/100), sprayThreeFour[0].averageTrees),
                calcVariance((double)(averageTreesThreeFour[1]/100), sprayThreeFour[1].averageTrees),
                calcVariance((double)(averageTreesThreeFour[2]/100), sprayThreeFour[2].averageTrees),
                calcVariance((double)(averageTreesThreeFour[3]/100), sprayThreeFour[3].averageTrees));
    }

    public static void calcGrowth(AcresTrees[] trees, int spraytype) {//calculates growth based on rainfall.
        int temp, temp1, temp2, temp3 = 0;
        if (trees[0].rainfallIn > 0 && trees[0].rainfallIn < 4) {//drought
            temp = (int) (trees[0].amountofAcres * .3);//only 30% will grow temp stores the ones that grow
            temp1 = (int) (trees[1].amountofAcres * .25);
            temp2 = (int) (trees[2].amountofAcres * .4);
            temp3 = (int) (trees[3].amountofAcres * .35);//ones that are harvested
            trees[0].amountofAcres *= .7;//ones that didnt grow
            trees[1].amountofAcres *= .75;
            trees[1].amountofAcres += temp;//year 1s to 2s
            trees[2].amountofAcres *= .6;
            trees[2].amountofAcres += temp1;//year 2 to three
            trees[3].amountofAcres *= .65;
            trees[3].amountofAcres += temp2;//three to fours
        } else if (trees[0].rainfallIn > 3 && trees[0].rainfallIn < 11) {//moderate rain
            temp = (int) (trees[0].amountofAcres * .99);//99% will grow temp stores the ones that grow
            temp1 = (int) (trees[1].amountofAcres * .98);
            temp2 = (int) (trees[2].amountofAcres * .98);
            temp3 = (int) (trees[3].amountofAcres * .99);//ones that are harvested
            trees[0].amountofAcres *= .01;//ones that didnt grow
            trees[1].amountofAcres *= .02;
            trees[1].amountofAcres += temp;//year 1s to 2s
            trees[2].amountofAcres *= .02;
            trees[2].amountofAcres += temp1;//year 2 to three
            trees[3].amountofAcres *= .01;
            trees[3].amountofAcres += temp2;//three to fours
        } else {//heavy rain
            temp = (int) (trees[0].amountofAcres * .98);//only 30% will grow temp stores the ones that grow
            temp1 = (int) (trees[1].amountofAcres * .97);
            temp2 = (int) (trees[2].amountofAcres * .97);
            temp3 = (int) (trees[3].amountofAcres * .96);//ones that are harvested
            trees[0].amountofAcres *= .02;//ones that didnt grow
            trees[1].amountofAcres *= .03;
            trees[1].amountofAcres += temp;//year 1s to 2s
            trees[2].amountofAcres *= .03;
            trees[2].amountofAcres += temp1;//year 2 to three
            trees[3].amountofAcres *= .04;
            trees[3].amountofAcres += temp2;//three to fours
        }

        if (spraytype == 0) {
            harvested += temp3;
            trees[0].averageTrees.add(trees[0].amountofAcres);
            trees[1].averageTrees.add(trees[1].amountofAcres);
            trees[2].averageTrees.add(trees[2].amountofAcres);
            trees[3].averageTrees.add(trees[3].amountofAcres);
            harvestedList.add(temp3);
            //below replants new trees up to hardcap of 1 million across the farm.
            if (trees[0].amountofAcres + trees[1].amountofAcres + trees[2].amountofAcres + trees[3].amountofAcres < 1000000) {
                int tempp = 0;
                tempp = 1000000 - (trees[0].amountofAcres + trees[1].amountofAcres + trees[2].amountofAcres + trees[3].amountofAcres);
                trees[0].amountofAcres += tempp;
            }
        } else if (spraytype == 1) {//three year old sprayed
            harvested1 += temp3;
            harvestedList1.add(temp3);
            trees[0].averageTrees.add(trees[0].amountofAcres);
            trees[1].averageTrees.add(trees[1].amountofAcres);
            trees[2].averageTrees.add(trees[2].amountofAcres);
            trees[3].averageTrees.add(trees[3].amountofAcres);
            //below replants new trees up to hardcap of 1 million across the farm.
            if (trees[0].amountofAcres + trees[1].amountofAcres + trees[2].amountofAcres + trees[3].amountofAcres < 1000000) {
                int tempp = 0;
                tempp = 1000000 - (trees[0].amountofAcres + trees[1].amountofAcres + trees[2].amountofAcres + trees[3].amountofAcres);
                trees[0].amountofAcres += tempp;
            }
        } else {
            harvested2 += temp3;
            harvestedList2.add(temp3);
            trees[0].averageTrees.add(trees[0].amountofAcres);
            trees[1].averageTrees.add(trees[1].amountofAcres);
            trees[2].averageTrees.add(trees[2].amountofAcres);
            trees[3].averageTrees.add(trees[3].amountofAcres);
            //below replants new trees up to hardcap of 1 million across the farm.
            if (trees[0].amountofAcres + trees[1].amountofAcres + trees[2].amountofAcres + trees[3].amountofAcres < 1000000) {
                int tempp = 0;
                tempp = 1000000 - (trees[0].amountofAcres + trees[1].amountofAcres + trees[2].amountofAcres + trees[3].amountofAcres);
                trees[0].amountofAcres += tempp;
            }
        }
    }
    public static void beetleAndDehydroDeaths(AcresTrees[] trees) {//handles dry deaths and beetle deaths
        if (trees[0].sprayType == 0) {//unsprayed
            if (trees[0].rainfallIn > 0 && trees[0].rainfallIn < 4) {//drought
                trees[0].amountofAcres *= .80;
                trees[1].amountofAcres *= .75;
                trees[2].amountofAcres *= .40;
                trees[3].amountofAcres *= .35;
            } else if (trees[0].rainfallIn > 3 && trees[0].rainfallIn < 11) {//moderate rain
                trees[0].amountofAcres *= .90;
                trees[1].amountofAcres *= .9;
                trees[2].amountofAcres *= .85;
                trees[3].amountofAcres *= .85;
            } else {//heavy rain
                trees[0].amountofAcres *= .98;
                trees[1].amountofAcres *= .97;
                trees[2].amountofAcres *= .95;
                trees[3].amountofAcres *= .93;
            }
        } else if (trees[0].sprayType == 1) {//spray three year olds only
            if (trees[0].rainfallIn > 0 && trees[0].rainfallIn < 4) {//drought
                trees[0].amountofAcres *= .80;
                trees[1].amountofAcres *= .75;
                trees[2].amountofAcres *= .70;
                trees[3].amountofAcres *= .35;
            } else if (trees[0].rainfallIn > 3 && trees[0].rainfallIn < 11) {//moderate rain
                trees[0].amountofAcres *= .90;
                trees[1].amountofAcres *= .9;
                trees[2].amountofAcres *= .95;
                trees[3].amountofAcres *= .85;
            } else {//heavy rain
                trees[0].amountofAcres *= .98;
                trees[1].amountofAcres *= .97;
                trees[2].amountofAcres *= .98;
                trees[3].amountofAcres *= .93;
            }
        } else {//spray half the three and half the four year olds
            if (trees[0].rainfallIn > 0 && trees[0].rainfallIn < 4) {//drought
                trees[0].amountofAcres *= .80;
                trees[1].amountofAcres *= .75;
                trees[2].amountofAcres *= .55;
                trees[3].amountofAcres *= .50;
            } else if (trees[0].rainfallIn > 3 && trees[0].rainfallIn < 11) {//moderate rain
                trees[0].amountofAcres *= .90;
                trees[1].amountofAcres *= .9;
                trees[2].amountofAcres *= .90;
                trees[3].amountofAcres *= .90;
            } else {//heavy rain
                trees[0].amountofAcres *= .98;
                trees[1].amountofAcres *= .97;
                trees[2].amountofAcres *= .96;
                trees[3].amountofAcres *= .94;
            }
        }
    }

    public static double determineRainfall() {
        double percentChance = 0;
        percentChance = Math.random();


        return percentChance;
    }

    public static double determineFire(int rainfall) {
        double fireChance = 0;
        double temp = 0;//holds random chance for fire
        if (rainfall == 1) {
            fireChance = .90;
        } else if (rainfall == 2) {
            fireChance = .85;
        } else if (rainfall == 3) {
            fireChance = .65;
        } else if (rainfall == 4) {
            fireChance = .55;
        } else if (rainfall == 5) {
            fireChance = .45;
        } else if (rainfall == 6) {
            fireChance = .3;
        } else if (rainfall == 7) {
            fireChance = .15;
        } else if (rainfall == 8) {
            fireChance = .10;
        } else if (rainfall == 9) {
            fireChance = .05;
        } else if (rainfall == 10) {
            fireChance = .03;
        } else if (rainfall == 11) {
            fireChance = .02;
        } else {
            fireChance = .01;
        }

        return fireChance;
    }
    public static double calcVariance(double average, ArrayList<Integer> list){
        double variance=0;
        double temp=0;
        for (int i =0; i<100; i++){// summation of x sub i minus x bar squared
            temp+= Math.pow((list.get(i))-average,2);
        }
        variance= temp/10000000; //divides by N
        return variance;
    }
}


class AcresTrees {
    int amountofAcres;
    int type;
    int rainfallIn;
    ArrayList<Integer> averageTrees= new ArrayList<>();

    int sprayType;//0 unsprayed, 1 three year olds, 2 half three and four year olds.

    public AcresTrees(int trees, int seedingType, int sprayType) {
        this.amountofAcres = trees;
        this.type = seedingType;
        this.sprayType = sprayType;
    }

    public int getAmountofAcres() {
        return amountofAcres;
    }

    public void setAmountofAcres(int amountofAcres) {
        this.amountofAcres = amountofAcres;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRainfallIn() {
        return rainfallIn;
    }

    public void setRainfallIn(int rainfallIn) {
        this.rainfallIn = rainfallIn;
    }

    public ArrayList<Integer> getAverageTrees() {
        return averageTrees;
    }

    public void setAverageTrees(ArrayList<Integer> averageTrees) {
        this.averageTrees = averageTrees;
    }

    public int getSprayType() {
        return sprayType;
    }

    public void setSprayType(int sprayType) {
        this.sprayType = sprayType;
    }

    public int determineRainAmount(double chance) {
        int rainInches = 0;
        if (this.type == 0) {//non Seeded clouds
            if (chance < .01) {
                rainInches = 1;
            } else if (chance < .06) {
                rainInches = 2;
            } else if (chance < .11) {
                rainInches = 3;
            } else if (chance < .14) {
                rainInches = 4;
            } else if (chance < .24) {
                rainInches = 5;
            } else if (chance < .39) {
                rainInches = 6;
            } else if (chance < .59) {
                rainInches = 7;
            } else if (chance < .73) {
                rainInches = 8;
            } else if (chance < .83) {
                rainInches = 9;
            } else if (chance < .93) {
                rainInches = 10;
            } else if (chance < .98) {
                rainInches = 11;
            } else {
                rainInches = 12;
            }
        } else {//seeded
            if (chance < .01) {
                rainInches = 1;
            } else if (chance < .02) {
                rainInches = 2;
            } else if (chance < .03) {
                rainInches = 3;
            } else if (chance < .05) {
                rainInches = 4;
            } else if (chance < .15) {
                rainInches = 5;
            } else if (chance < .3) {//assignment only had a percent chance totaling to 90%. Added an extra 5% to both 6 in. and 8 in.
                rainInches = 6;
            } else if (chance < .5) {
                rainInches = 7;
            } else if (chance < .65) {
                rainInches = 8;
            } else if (chance < .75) {
                rainInches = 9;
            } else if (chance < .85) {
                rainInches = 10;
            } else if (chance < .95) {
                rainInches = 11;
            } else {
                rainInches = 12;
            }
        }
        return rainInches;
    }
}