
from flask import Flask, render_template, request, redirect, session, Blueprint
import datetime as dt
import auth

app = Flask(__name__)
# app.secret_key = b'_lkdiokHlkjl*eoiZKLGUIkreiuh' + str(dt.datetime.now()).encode('utf-8')
app.secret_key = b'dev'

app.register_blueprint(auth.bp)

@app.route("/")
@app.route("/index")
def index():
    """home page"""
    return render_template('index.html')
    