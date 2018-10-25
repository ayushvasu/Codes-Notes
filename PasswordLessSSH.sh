# https://www.tecmint.com/ssh-passwordless-login-using-ssh-keygen-in-5-easy-steps/

ssh-keygen -t rsa

ssh username@192.168.0.11 mkdir -p .ssh
# Password is required 

cat .ssh/id_rsa.pub | ssh username@192.168.0.11 'cat >> .ssh/authorized_keys'
# Password is required 

ssh username@192.168.0.11 "chmod 700 .ssh; chmod 640 .ssh/authorized_keys"
# Password is required 

ssh username@192.168.0.11
