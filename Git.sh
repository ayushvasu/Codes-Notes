#add ssh key
ssh-keygen -t ed25519 -C "ayushvasu98@gmail.com"
#start the agent
eval "$(ssh-agent -s)"
#create config file in ~/.ssh
touch ~/.ssh/config
#Add this content in that file 
#Host github.com
#  AddKeysToAgent yes
#  UseKeychain yes
#  IdentityFile ~/.ssh/id_ed25519
#Add key to agent
ssh-add --apple-use-keychain ~/.ssh/id_ed25519

#add key to git hub and than clone the repo ...
