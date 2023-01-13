# programming from mosh , 2:26:00
phone = input("Phone: ")
digits_mapping  = {
    "1"  : "one",
    "2" : "Two",
    "3" : "Three"
 }
output = ""
for ch in phone:
    output +=digits_mapping.get(ch,"!") + " "
print(output)

