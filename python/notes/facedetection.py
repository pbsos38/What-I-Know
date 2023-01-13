# Face detection
#create a cascadeclassifier object, it will contain the features of face
import cv2
face_cascade = cv2.CascadeClassifier("C:\\Python38\\Lib\\xml\\haarcascade_frontalface_default.xml")

#reading the image as it is
img = cv2.imread("D:\\prince\\Pictures\\DSC_8022 (2).jpg")

#reading the image as gray scale image
gray_img = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)

#serach to co-ordinates of the imageMultiscale is the method to search for the face rectangel co-ordinates
#scale factor will decrease the shape  value by 5%, until  the face is found, smaller this value, the greater is the accuracy   
faces = face_cascade.detectMultiScale(gray_img,scaleFactor=1.05,minNeighbors=5)
#detect
print(faces)#tells the co ordinartes
for x,y,w,h in faces:
    #x = x-co ordinate
    #y = y- co ordinate
    #w = width
    #h = height
    img = cv2.rectangle(img,(x,y), (x+w,y+h),(0,255,0),3)
    #rectangle - method to create face rectangle
    #(0,255,0) - rgb value of the rectangle outline
    # 3    width of rectangle
cv2.imshow("gray",img)
cv2.waitKey()
cv2.destroyAllWindows()
