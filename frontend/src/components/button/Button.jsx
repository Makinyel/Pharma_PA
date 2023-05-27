import React from "react";
import { MainButton } from "./Button.styles";

const Button = ({ type, text, onClick }) => {
  const handleClick = () => {
    if (onClick) {
      onClick();
    }
  };

  return (
    <MainButton onClick={handleClick} type={type}>
      {text}
    </MainButton>
  );
};

export default Button;
