1. Clone Repository
   - Ensure git is intsalled (link to download git -> https://git-scm.com/)
   - open Android Studio, go to File → New → Project from Version Control → Git, and paste the repository URL.

2. Link Android Studio to GitHub
   - Go to File → Settings → Version Control and ensure Git is set up.
   - Use File → Settings → Version Control → GitHub to authenticate with your GitHub account.

3. Collaboration workflow
   - Each team member works on their task branch (Ex: frontend-donation, backend-donation)

3.1. Push
   - open terminal in android studio
   - "git checkout branch_name", change branch_name with the name of branch you want to work on
   - "git add file_name", change file_name with file you want to include in your commit OR "git add .", to add all file
   - "git commit -m "Your commit message", write meaningful commit message
   - "git push origin branch_name", push the branch to remote repository, origin refers to the default remote repository and branch_name is the branch you’re working on.

3.2 Pull
   - "git checkout branch_name", before pulling, ensure you're on the correct branch
   - "git pull origin branch_name", fetch the latest update from the remote 

**EXTRA**
-"git branch -r", to view all branches in remote repository

# main BRANCH IS ONLY TO MERGE THE PARTS THAT HAS BEEN FINALISED
