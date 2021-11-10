from flask import Flask
from flask import request
from flask import render_template

design = Flask(__name__)

@design.route("/")
def main():
    return render_template("index.html")

@design.route('/home.html')
def home():
    return render_template('home.html')

@design.route('/login.html')
def login():
    return render_template('login.html')

@design.route('/register.html')
def register():
    return render_template('register.html')
    
    
if __name__ == "__main__":
    design.run(host="0.0.0.0", port=5050)