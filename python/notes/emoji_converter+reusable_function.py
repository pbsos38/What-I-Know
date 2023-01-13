# emoji_converter+reusable_function.py

#  from programming with mosh
# don't know how it can be used as reuseable function

def emoji(message):
    words = message.split(" ")
    emojis = {
        ":)": "😀",
        ":(": "😥"
    }

    output = ""
    for word in words:
        output += emojis.get(word,word) + " "

message = input(">")
print(emoji(message))