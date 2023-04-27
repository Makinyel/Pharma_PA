import { useEffect, useState } from "react";

import Button from "../../components/button/Button";
import Input from "../../components/input/Input";

import {
  medicamentosFormInfo,
  personasFormInfo,
  bodegaFormInfo,
  seccionesFormInfo,
  correosFormInfo,
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
    if (tab === "medicamentos") {
      setFormInfo(medicamentosFormInfo);
    } else if (tab === "personas") {
      setFormInfo(personasFormInfo);
    } else if (tab === "bodega") {
      setFormInfo(bodegaFormInfo);
    } else if (tab === "secciones") {
      setFormInfo(seccionesFormInfo);
    } else if (tab === "correos") {
      setFormInfo(correosFormInfo);
    }
  });

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
