import { useEffect, useState } from "react";

import Button from "../../components/button/Button";
import Input from "../../components/input/Input";

import {
  productosFormInfo,
  marcaFormInfo,
} from "../../utils/form-info/gestionFormInfo";

import {
  GestionContentWrapper,
  GestionContentHeader,
  GestionContentFormWrapper,
  GestionContentButtonWrapper,
} from "./GestionContent.styles";

import { useMediaQuery } from "@mui/material";

const GestionContent = ({ tab }) => {
  const [formInfo, setFormInfo] = useState([]);
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");

  useEffect(() => {
    if (tab === "producto") {
      setFormInfo(productosFormInfo);
    } else if (tab === "marca") {
      setFormInfo(marcaFormInfo);
    } else {
      setFormInfo([]);
    }
  }, [tab]);

  return (
    <GestionContentWrapper>
      <GestionContentHeader>Agregar {tab}</GestionContentHeader>
      <GestionContentFormWrapper>
        <form>
          {formInfo.map(({ title, description, type }, index) => (
            <Input
              type={type}
              key={index}
              title={title}
              description={description}
            />
          ))}
          <GestionContentButtonWrapper isNotAPhone={isNotAPhone}>
            <Button text="Guardar" />
          </GestionContentButtonWrapper>
        </form>
      </GestionContentFormWrapper>
    </GestionContentWrapper>
  );
};

export default GestionContent;
