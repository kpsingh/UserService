# UserService

If your local branch is one commit behind the `main` branch and you want to pull the latest changes while keeping your local changes, you can use `git pull --rebase`. This command will apply your local changes on top of the latest changes from `main`, making the history cleaner.

Here’s how to do it:

1. First, make sure you are on your local branch:
   ```bash
   git checkout your-branch-name
   ```

2. Pull and rebase with the `main` branch:
   ```bash
   git pull --rebase origin main
   ```

   This command will pull the latest changes from the `main` branch and then apply your local commits on top of the changes from `main`.

3. If there are any conflicts, Git will prompt you to resolve them. Once resolved, you can continue the rebase by running:
   ```bash
   git rebase --continue
   ```

4. After completing the rebase, push your changes to your remote branch. You may need to use `--force-with-lease` if you have rewritten the commit history:
   ```bash
   git push --force-with-lease
   ```

This process will leave you with a linear commit history that includes both `main` branch changes and your local changes.
