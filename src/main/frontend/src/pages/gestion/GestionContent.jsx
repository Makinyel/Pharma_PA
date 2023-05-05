import { useEffect, useState, useRef } from "react";

import Button from "../../components/button/Button";
import Input from "../../components/input/Input";

import {
  productosFormInfo,
  marcaFormInfo,
  presentacionFormInfo,
  bodegaFormInfo,
  concentracionFormInfo,
} from "../../utils/form-info/gestionFormInfo";

import {
  GestionContentWrapper,
  GestionContentHeader,
  GestionContentFormWrapper,
  GestionContentButtonWrapper,
  InputRow,
  InputWrapper,
} from "./GestionContent.styles";

import { useMediaQuery } from "@mui/material";
import { clearFormFields } from "../../utils/functions/formUtils";

import _ from "lodash";

const GestionContent = ({ tab }) => {
  const [formInfo, setFormInfo] = useState([]);
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");
  const formRef = useRef(null);

  const formInfoMap = {
    producto: productosFormInfo,
    marca: marcaFormInfo,
    presentacion: presentacionFormInfo,
    bodega: bodegaFormInfo,
    concentracion: concentracionFormInfo,
  };

  useEffect(() => {
    setFormInfo(formInfoMap[tab] || []);
  }, [tab]);

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    const promises = ["marca", "presentacion", "bodega", "concentracion"].map(
      async (fieldName) => {
        const response = await fetch(
          `http://localhost:8080/${fieldName}/nombre?name=${formData.get(
            fieldName
          )}`
        );
        const data = await response.json();
        return [fieldName, data];
      }
    );

    const results = await Promise.all(promises);

    const body = {
      nombre: formData.get("nombre"),
      descripcion: formData.get("descripcion"),
      stock: parseInt(formData.get("stock")),
      precioCosto: parseFloat(formData.get("precio costo")),
      precioVenta: parseFloat(formData.get("precio venta")),
    };

    results.forEach(([fieldName, data]) => {
      body[fieldName] = data;
    });

    const response = await fetch(`http://localhost:8080/producto`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    });

    if (response.ok) {
      alert("Producto creado con éxito");
      clearFormFields(formRef);
    } else {
      alert("Error al crear el producto");
    }
  };

  return (
    <GestionContentWrapper>
      <GestionContentHeader>Agregar {tab}</GestionContentHeader>
      <GestionContentFormWrapper>
        <form ref={formRef} onSubmit={handleFormSubmit}>
          <InputRow>
            {formInfo.map(({ title, description, type }) => (
              <InputWrapper key={title}>
                <Input title={title} description={description} type={type} />
              </InputWrapper>
            ))}
          </InputRow>
          <GestionContentButtonWrapper isNotAPhone={isNotAPhone}>
            <Button type="submit" text="Guardar" />
          </GestionContentButtonWrapper>
        </form>
      </GestionContentFormWrapper>
    </GestionContentWrapper>
  );
};

export default GestionContent;
