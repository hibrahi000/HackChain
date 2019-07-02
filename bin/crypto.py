from cryptography.fernet import Fernet
import argparse
from sys import argv
from os.path import isfile
from os import getcwd
 
 
def generateKey():
    key = Fernet.generate_key()
    print(key)
    with open('my.key1', mode='a') as f:
        f.write(bytes.decode(key) + '\n')
    return key

parser = argparse.ArgumentParser()
group = parser.add_mutually_exclusive_group()
group.add_argument('-e', '--encrypt', metavar='PLAINTEXT', help='The plaintext to encrypt.')
group.add_argument('-d', '--decrypt', metavar='CIPHERTEXT', help='The ciphertext to decrypt.')
otherGroup = parser.add_mutually_exclusive_group(required=True)
otherGroup.add_argument('-k', '--key', metavar='/path/to/my.key', help="The key to use for encryption/decryption.")
otherGroup.add_argument('--genkey', action='store_true', help="Generate a key. Can be used alone, or with --encrypt.")
 
if len(argv) == 1:
    argv.append('--help')
 
args = parser.parse_args()
 
if args.decrypt and args.genkey:
    print("--decrypt requires an input key file using --key\n")
    exit(1)
 
if args.key:
    if isfile(args.key):
        with open(args.key) as f:
            key = f.readline()
elif args.genkey:
    key = generateKey()
    print("Key saved as {}/my.key1".format(getcwd()))



 
try:
    if args.encrypt:   
        f = Fernet(key) 
        result = bytes.decode(f.encrypt(str.encode(args.encrypt)))
 
    elif args.decrypt:
        f = Fernet(args.key)
        result = bytes.decode(f.decrypt(str.encode(args.decrypt)))
 
    print(result)
except:
    print("Something weird happened.")
    exit(1)