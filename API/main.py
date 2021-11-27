from flask import Flask, url_for, redirect, jsonify
from flask import request
from flask import render_template
import sqlite3 as sql
from flask_sqlalchemy import SQLAlchemy
from flask_marshmallow import Marshmallow


app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///users_database.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = True
db = SQLAlchemy(app)
ma = Marshmallow(app)

class User(db.Model):
    __tablename__="user"
    user_id = db.Column(db.Integer, primary_key = True)
    user_email = db.Column(db.String(50))
    user_username = db.Column(db.String(50))
    user_password = db.Column(db.String(50))
    

    def __init__(self,user_email,user_username,user_password):
        self.user_email = user_email
        self.user_username = user_username
        self.user_password = user_password
    
class UserSchema(ma.Schema):
    class Meta:
        fields = ("user_email", "user_username", "user_password")

user_schema = UserSchema()
users_schema = UserSchema(many = True)

@app.route('/dashboard', methods=['GET', 'POST'])
def dashboard():
    return render_template('dashboard.html')

@app.route('/')
@app.route('/index', methods=['GET', 'POST'])
def index():
 return render_template('index.html')

@app.route('/login', methods=['POST'])
def login():
    username = request.json.get('username')
    password = request.json.get('password')

    user = User.query.filter_by(user_username=username).first()

    if(user):
        if password == user.user_password:
            return {"message": "logged in successfully"}
        else:
            return {"message": "username or password is incorrect"}
    else:
        return {"message": "user does not exist"}
    
@app.route('/register', methods=['POST'])
def register():
    if request.method == 'POST':
        email = request.json.get('email')
        username = request.json.get('username')
        password = request.json.get('password')

        new_user = User(user_email=email,user_username=username,user_password=password)

        db.session.add(new_user)
        db.session.commit()

        return jsonify({"message":"registration success"})
            


if __name__ == '__main__':
    app.run(host="0.0.0.0", port=5000)