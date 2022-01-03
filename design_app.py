from flask import Flask
from flask import request
from flask import render_template
from requests import get, post
from flask import Flask, url_for, redirect, jsonify


app = Flask(__name__)

@app.route("/")
def main():
    return render_template("index.html")

@app.route('/dashboard')
def home():
    return render_template('dashboard.html')

@app.route('/login', methods=["GET", "POST"])
def login():
    if request.method == "GET":
        return render_template('login.html')
    else:
        email = request.form.get('email')
        username = request.form.get('username')
        password = request.form.get('password')

        body = {"username": username, "password": password}
        login_url = "http://10.0.2.15:5000/login"
        response = post(login_url, json=body)

        response = response.json()

        if response["message"] == "logged in successfully":
            return redirect('/dashboard')

        else: 
            return redirect('/login')

@app.route('/register', methods=["GET", "POST"])
def register():
    if request.method == "GET":
        return render_template('register.html')
    else:
        email = request.form.get('email')
        username = request.form.get('username')
        password = request.form.get('password')

        body = {"email": email, "username": username, "password": password}
        register_url = "http://10.0.2.15:5000/register"
        response = post(register_url, json=body)

        return redirect('/login')

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5050)