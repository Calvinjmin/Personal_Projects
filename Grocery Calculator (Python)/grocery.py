calvin = []
mitch = []
josh = []
mike = []

def main():
    while True:
        end = input("Do you have any more items left to add? ")
        if end == "n":
            break

        item = input("Item: ")
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
                    per = float(input( "Percentage of " + personPer + " "))
                    if ( totalPer + per > 1.0 ):
                        print("Invalid percentage.. Try Again!")
                    else:
                        addItem( personPer, round(price * per,2), item )
                        totalPer += per
                        break
    printItems()

def addItem( p, price, item) :
    if p == 'e':
        calvin.append(price)
        josh.append(price)
        mitch.append(price)
        mike.append(price)
    elif p == "c" or p == 'cal':
        print( "Calvin - " + item + "\nAmount - " + str(price))
        calvin.append( price)
    elif p == 'j' or  p == "josh":
        print( "Josh - " + item + "\nAmount - " + str(price))
        josh.append( price)
    elif p == 'mh' or p == "mitch":
        print( "Mitch - " + item + "\nAmount - " + str(price))
        mitch.append( price )
    elif p == 'mb' or p == "mike":
        print( "Mike - " + item + "\nAmount - " + str(price))
        mike.append( price )

def printItems():
    val = 0.00
    for x in calvin:
        val += x
    print( "Calvin pays " + str(round(val,2)) )

    val = 0.00
    for x in josh:
        val += x
    print( "Josh pays " + str(round(val,2)) )

    val = 0.00
    for x in mitch:
        val += x
    print( "Mitch pays " + str(round(val,2)) )

    val = 0.00
    for x in mike:
        val += x
    print( "Mike pays " + str(round(val,2)) )

if __name__ == "__main__":
    main()