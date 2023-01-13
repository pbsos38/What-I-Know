
# # web scraping

# #one need to fetch some info from the website. to so so, copy and paste  the data display by the website which is very tedious job that may take many hours or sometime days to complete

# # webscraping is a technique emplyed to extract large amount of data from websites wherby the data is extracted and saved to local file in your computer

from bs4 import BeautifulSoup as soup
from urllib.request import urlopen as uReq

my_url = 'https://www.flipkart.com/search?q=iphone&otracker=start&as-showb=on&as=off'

uCliet = uReq(my_url)
page_html = uCliet.read()
uCliet.close()
page_soup = soup(page_html, "html.parser")

containers = page_soup.findAll("div",{"class": "bhgxx2 col-12-12"}) # div of item 1 
#print(len(containers))

#print(soup.prettify(containers[0]))

container = containers[0]
#print(container.div.img["alt"])

price = container.findAll("div",{"class":"col col-5-12 _2o7WAb"})
print(price[0].text)
rating = container.findAll("div",{"class":"niH0FQ"})
#print(rating[0].text)

# filename="webscrapping.csv"

# f=open(filename,"w")

# headers="product name,pricing,ratings\n"
# f.write(headers)


# for container in containers:
#     products_name = containers.div.img["alt"]

#     product_price = container.findAll("div",{"class":"col col-5-12 _2o7WAb"})
#     price = price_container[0].text.strip()

#     rating_container = container.findAll("div",{"class":"niH0FQ"})
#     rating = rating_container[0].text

#     print("product_name:" + products_name)
#     print("price:" + price)
#     print("rating:" + rating)

#     #string prasing
#     trim_price = ''.join(price.split(','))
#     rm_rupee = trim_price.split("$")
#     add_rs_price = "rs." + rm_rupee[1]
#     split_price = add_rs_price.split('E')
#     final_price = split_price[0]

#     split_rating = rating.split(" ")
#     final_rating = split_rating[0]

#     print(products_name.replace(",", "|") + "," + final_price + "," + final_rating + "\n")
#     f.write(products_name.replace(",", "|") + "," + final_price + "," + final_rating + "\n")

# f.close()

