import { useEffect, useState, useRef } from "react";

import Button from "../../components/button/Button";
import Input from "../../components/input/Input";

import {
  productsFormInfo,
  brandFormInfo,
  preparationFormInfo,
  warehouseFormInfo,
  concentrationFormInfo,
} from "../../utils/form-info/productsFormInfo";

import {
  GestionContentWrapper,
  GestionContentHeader,
  GestionContentFormWrapper,
  GestionContentButtonWrapper,
  InputRow,
  InputWrapper,
} from "./ProductsContentStyles.jsx";

import { useMediaQuery } from "@mui/material";
import { clearFormFields } from "../../utils/functions/formUtils";
import { endpoints } from "../../utils/endpoints/endpoints";

import _ from "lodash";

const ProductsContent = ({ tab }) => {
  const [formInfo, setFormInfo] = useState([]);
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");
  const formRef = useRef(null);

  const formInfoMap = {
    product: productsFormInfo,
    brand: brandFormInfo,
    preparation: preparationFormInfo,
    warehouse: warehouseFormInfo,
    concentration: concentrationFormInfo,
  };

  useEffect(() => {
    setFormInfo(formInfoMap[tab] || []);
  }, [tab]);

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    const marcaName = formData.get("brand");
    const presentacionName = formData.get("preparation");
    const concentracionName = formData.get("concentration");

    const tabEndpoints = {
      product: endpoints.product.save,
      brand: endpoints.brand.save,
      preparation: endpoints.preparation.save,
      warehouse: endpoints.warehouse.save,
      concentration: endpoints.concentration.save,
    };

    const endpoint = tabEndpoints[tab];

    try {
      const response = await fetch(endpoint, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          nombre: formData.get("name"),
          descripcion: formData.get("description"),
          precioCosto: parseFloat(formData.get("buying price")),
          precioVenta: parseFloat(formData.get("selling price")),
          marca: marcaName,
          presentacion: presentacionName,
          concentracion: concentracionName,
        }),
      });

      if (response.ok) {
        alert(`${_.upperFirst(tab)} has been successfully saved.`);
        clearFormFields(formRef);
      } else {
        alert(`There was an error saving the ${tab}.`);
      }
    } catch (error) {
      console.error("Error saving the " + tab, error);
      alert("There was an error saving the product.");
    }
  };

  return (
    <GestionContentWrapper>
      <GestionContentHeader>Add a new {tab}</GestionContentHeader>
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
            <Button type="submit" text="Save" />
          </GestionContentButtonWrapper>
        </form>
      </GestionContentFormWrapper>
    </GestionContentWrapper>
  );
};

export default ProductsContent;
