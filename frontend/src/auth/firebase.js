import { initializeApp } from "firebase/app";
import {
  createUserWithEmailAndPassword,
  getAuth,
  signInWithEmailAndPassword,
  signOut,
} from "firebase/auth";
import { getFirestore, doc, setDoc } from "firebase/firestore";
import { setUser } from "../state/state";
import { endpoints } from "../utils/endpoints/endpoints";

const firebaseConfig = {
  apiKey: "AIzaSyD5hstV5px76g-ijHHx6m2ujDkTilkX-eM",
  authDomain: "pharma-pa.firebaseapp.com",
  projectId: "pharma-pa",
  storageBucket: "pharma-pa.appspot.com",
  messagingSenderId: "775355288768",
  appId: "1:775355288768:web:3c8ad00046a7f949ac3125",
};

export const firebaseApp = initializeApp(firebaseConfig);
export const auth = getAuth(firebaseApp);
export const firestore = getFirestore(firebaseApp);

export const createAccount = async (userInformation) => {
  const { name, email, password, address, phone, role } = userInformation;

  const user = await createUserWithEmailAndPassword(auth, email, password)
    .then((res) => {
      return res.user;
    })
    .catch((err) => console.log(err));

  const documentReference = doc(firestore, `/users/${user.uid}`);

  const userToSave = { name, email, address, phone, role };

  setDoc(documentReference, userToSave);

  await fetch("http://localhost:8080/users", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(userToSave),
  });
};

export const signIn = async (email, password) => {
  if (!email && !password) return;
  await signInWithEmailAndPassword(auth, email, password)
    .then((res) => {
      return res.user;
    })
    .catch((err) => {
      let alertMessage = "";
      switch (err.code) {
        case "auth/wrong-password":
          alertMessage = "Wrong user/password";
          break;
        case "auth/too-many-requests":
          alertMessage = "Too many request. Try later";
          break;
      }
      alert(alertMessage);
    });
  const user = await fetch(endpoints.user.getByEmail(email)).then((res) =>
    res.json()
  );
  return user;
};

export const signOutUser = () => {
  return async (dispatch) => {
    try {
      await signOut(auth);
      dispatch(setUser({ user: {} }));
    } catch (error) {
      console.log(error);
    }
  };
};
