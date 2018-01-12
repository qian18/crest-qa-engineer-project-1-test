# crest-qa-engineer-project-1-test
crest-qa-engineer-project-1-test
This project is created in Eclipse and all of the methods passed. To run it, you need to assign valid username and password to USERNAME and PASSORD in the code to pass the tests. 
	Here are a few things I would like to mention for this project:
1.	Added System.setProperty() in Setup()
2.	Added “throws InterruptedException” for each method
3.	Added testGitHubLoginFailure2() to check “an unknown user with a known (or wrong) password”
4.	Have to add “wait()” after creating a repository with an existing repository name, otherwise you will see “unable to locate the element…”. If no waiting, the program runs too fast and the related xml code is not generated yet when we call findElement(By.Xpath()) . This issue bothered me half day before I figured it out.

	There are some more things to do to make this project better. For example:
1.	Before login, it is better to check the current webpage is the login page, then login
2.	In testStartAProjectSuccess(), need to check if the current input repository name exists or not, if no, then execute the following codes.
3.	Due to time limitation, I hard coded some values. For example:
a). In testStartAProjectSuccess(), hard coded the new repository name (String str = "qatest2").  It will be better for the user input the name from the keyboard. So when you run this method, you have to change it to a new name if qatest2 already exists. 
b). In testStartAProjectFailure() , “crest-qa-engineer-project-1-test” is an existing repository name, I hard coded it in sendKeys(“crest-qa-engineer-project-1-test”) . Here we should iterate all available repositories for this owner to see if the current name match the existing ones or not.  So when you run this method, you have to create a repository named “crest-qa-engineer-project-1-test” if it doesn’t exist. 
