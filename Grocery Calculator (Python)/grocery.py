person_a = []
person_c = []
person_b = []
person_d = []

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
        person_a.append(price)
        person_b.append(price)
        person_c.append(price)
        person_d.append(price)
    elif p == "c" or p == 'cal':
        print( "person_a - " + item + "\nAmount - " + str(price))
        person_a.append( price)
    elif p == 'j' or  p == "person_b":
        print( "person_b - " + item + "\nAmount - " + str(price))
        person_b.append( price)
    elif p == 'mh' or p == "person_c":
        print( "person_c - " + item + "\nAmount - " + str(price))
        person_c.append( price )
    elif p == 'mb' or p == "person_d":
        print( "person_d - " + item + "\nAmount - " + str(price))
        person_d.append( price )

def printItems():
    val = 0.00
    for x in person_a:
        val += x
    print( "person_a pays " + str(round(val,2)) )

    val = 0.00
    for x in person_b:
        val += x
    print( "person_b pays " + str(round(val,2)) )

    val = 0.00
    for x in person_c:
        val += x
    print( "person_c pays " + str(round(val,2)) )

    val = 0.00
    for x in person_d:
        val += x
    print( "person_d pays " + str(round(val,2)) )

if __name__ == "__main__":
    main()