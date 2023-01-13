#open cv
#it is a librar of python designed to solve computer vision problems
import cv2

img = cv2.imread("D:\\prince\\Pictures\\DSC_8022 (2).jpg",1)
# one stands for color image
img2 = cv2.imread("D:\\prince\\Pictures\\DSC_8022 (2).jpg",0)
# zero stands for black and white image
print(img)
print(img2)

print(type(img))
print(type(img2))
print(img.shape)# at end of results 3 stands for 3 channel image
print(img2.shape)

cv2.imshow("hi",img)
cv2.imshow("hi",img2)
cv2.waitKey(0)
#cv2.waitKey(2000)

cv2.destroyAllWindows()

#Resizing images

resized = cv2.resize(img,(600,600))
#resize_image = cv2.resize(img,(int(img.shape[1]/2),int(img.shape[0]/2)))
cv2.imshow("hi",resized)
cv2.waitKey(2000)
cv2.destroyAllWindows()
