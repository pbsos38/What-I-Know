print("hello")
"hi"
# for jupyter""" comment anything""" this will be shown in output if there is no code after 3pairs of ' " '







# import cv2, time
# vedio = cv2.VideoCapture(0)# this will read the first frame/image of the vedio
                #0 in vedio capture is for primary camera, 1 for secondary camera

# check,frame = vedio.read()
# # check - it is bool data type, returns true if python is able to read the capture object
# # frame -  it is numy array, it represents the first image that vedio captures

# print(check)# it returns true if python is able ot read camera
# print(frame)# it will return the the 3-d array 
# time.sleep(3)
# cv2.imshow('capturing',frame)
# cv2.waitKey(0)
# vedio.release()
# cv2.destroyAllWindows()

#how to capture the vedio,, instead of first image/frame of the vedio
                    #not working
# import cv2,time
# vedio = cv2.VideoCapture(0)
# a = 1

# while True:
#     a = a+1
#     check,frame = vedio.read()
#     print(frame)

#     gray = cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)

#     cv2.waitKey(1) #this will generate a new frame after every 1 milliseconds
#     key = cv2.waitKey(10000)
#     if key == ord('q'):   
#         #once we press q the window will destroy
#         break

# print(a)# this will print the number of frames
# vedio.release()
# cv2.destroyAllWindows()

#  motion detector
# import cv2,time,pandas
# from datetime import datetime

# first_frame = None
# status_list = [None,None]
# times = []
# df=pandas.DataFrame(columns=["start","end"])

# vedio = cv2.VideoCapture(0)# create a vesdiom capture object to record vedio using web cam

# while True:
#     check,frame = vedio.read()
#     status = 0
#     gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)#convert the frame color to gray scale
#     gray = cv2.GaussianBlur(gray,(21,21),0)# convert the gray scale frame to guassianblur

#     if first_frame is None:             #used to store first image/frame of vedio
#         first_frame = gray
#         continue
# delta_frame
