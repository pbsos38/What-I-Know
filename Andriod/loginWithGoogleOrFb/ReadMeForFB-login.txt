1) visit ' developers.facebook.com ' and login
2) click on docs and search for andriod - facebook login
3) now create new app
4) copy the gradle script and paste in the project
5) copy strings file and paste in project 
6) copy the meta data file and paste (in manifest file) between application and activity brackets
7) in " Associate Your Package Name and Default Class with Your App " enter all info and click on save and croos te alert box
8) copy development key and paste in local terminal 
9) if terminal says (for windows) the system cannt find the path specified then visit https://code.google.com/archive/p/openssl-for-windows/downloads
	and download the appropriate file 
10) incase system dont have jdk 9 install it 
11) go to cmd and run twice ' cd .. ' command and paste ' cd Program Files\Java\jdk-9.0.4\bin ' and run

12) make changes in development hash key
	
	a) replace 
			C:\Users\USERNAME\.android\debug.keystore 
					
					with 

			%USERPROFILE%\.android\debug.keystore  

	b) replace the openssl location
				with
			C:\openssl-0.9.8k_X64

before it looks


      
	keytool -exportcert -alias androiddebugkey -keystore "C:\Users\USERNAME\.android\debug.keystore" | "PATH_TO_OPENSSL_LIBRARY\bin\openssl" sha1 -binary | "PATH_TO_OPENSSL_LIBRARY\bin\openssl" base64
      

after 


      
	keytool -exportcert -alias androiddebugkey -keystore "%USERPROFILE%\.android\debug.keystore" | "C:\openssl-0.9.8k_X64\bin\openssl" sha1 -binary | "C:\openssl-0.9.8k_X64\bin\openssl" base64
      

so final command is
		
	 C:\Program Files\Java\jdk-9.0.4\bin>keytool -exportcert -alias androiddebugkey -keystore "%USERPROFILE%\.android\debug.keystore" | "C:\openssl-0.9.8k_X64\bin\openssl" sha1 -binary | "C:\openssl-0.9.8k_X64\bin\openssl" base64



set any password mine is andriod

13) paste the generated key in key hash coloumn(in website) and save
14) toogle to yes in enable sign in and save
15) rest is copy paste code 



