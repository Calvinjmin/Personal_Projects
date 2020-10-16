person_a = []
person_c = []
person_b = []
person_d = []

def main():
    itemNum = int(input("How many items did you buy? "))
    for iter in range(itemNum):
        item = input("Name of Item: ")
        price = float(input("Price: "))
        people = input("Who bought it? ")
        peopleA = people.split(",")
        numPeople= len(peopleA)

        splitEven = input("Is the item being split evenly? ")
        if people == 'e':
            addItem(people, float(price/4), item)
        elif splitEven != "n":
            pricePerPersonEven = float(price/numPeople)
            for p in peopleA:
                addItem( p , pricePerPersonEven, item )
        else:
            totalPer = 0
            for x in range(numPeople):
                while True:
                    personPer = input("Who? ")
                    per = float(input( "Percentage of " + personPer + " (Write in Decimal Format) "))
                    if ( totalPer + per > 1.0 ):
                        print("Invalid percentage.. Try Again!")
                    else:
                        addItem( personPer, round(price * per,2), item )
                        totalPer += per
                        break
        printIndvList()
    printFinalPay()

def addItem( p, price, item) :
    itemL = [item,price]
    if p == 'e':
        person_a.append(itemL)
        person_b.append(itemL)
        person_c.append(itemL)
        person_d.append(itemL)
    elif p == 'cal':
        person_a.append(itemL)
    elif p == "person_b":
        person_b.append(itemL)
    elif p == "person_c":
        person_c.append(itemL)
    elif p == "person_d":
        person_d.append(itemL)

def printIndvList():
    print("\nperson_a's List: ")
    for (item, x) in person_a:
        print( item + ": " + str(round(x,2) ))

    print("\nperson_b's List: ")
    for (item,x) in person_b:
        print( item + ": " + str(round(x,2) ))

    print("\nperson_cell's List: ")
    for (item,x) in person_c:
        print( item + ": " + str(round(x,2) ))

    print("\nperson_d's List: ")
    for (item,x) in person_d:
        print( item + ": " + str(round(x,2) ))

def printFinalPay():
    print("\n\n---Final Payments---")
    val = 0.00
    for (item, x) in person_a:
        val += x
    print("person_a pays " + str(round(val,2)) )

    val = 0.00
    for (item,x) in person_b:
        val += x
    print( "person_b pays " + str(round(val,2)) )

    val = 0.00
    for (item,x) in person_c:
        val += x
    print( "person_c pays " + str(round(val,2)) )

    val = 0.00
    for (item,x) in person_d:
        val += x
    print( "person_d pays " + str(round(val,2)) )

if __name__ == "__main__":
    main()