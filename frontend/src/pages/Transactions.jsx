import { React, useEffect, useRef, useState } from "react";
import { useSelector } from "react-redux";
import {
  buyFormInfo,
  sellFormInfo,
  transferFormInfo,
} from "../utils/form-info/transactionsFormInfo";

import Button from "../components/button/Button";
import Input from "../components/input/Input";

import {
  ButtonWrapper,
  FormWrapper,
  Header,
  InputRow,
  InputWrapper,
  Wrapper,
} from "../styles/FormStyles";

import { useMediaQuery } from "@mui/material";
import { endpoints } from "../utils/endpoints/endpoints";
import { clearFormFields } from "../utils/functions/formUtils";

import _ from "lodash";

const Transactions = () => {
  const transactionsTab = useSelector((state) => state.transactionsTab);
  const user = useSelector((state) => state.user);
  const [formInfo, setFormInfo] = useState([]);
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");
  const formRef = useRef(null);

  const formInfoMap = {
    buy: buyFormInfo,
    transfer: transferFormInfo,
    sell: sellFormInfo,
  };

  useEffect(() => {
    setFormInfo(formInfoMap[transactionsTab] || []);
  }, [transactionsTab]);

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    const tabEndpoints = {
      buy: endpoints.buy.base,
      sell: endpoints.sell.base,
      transfer: endpoints.transfer.base,
    };

    await fetch(tabEndpoints[transactionsTab], {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        cantidad: parseInt(formData.get("quantity")),
        producto: formData.get("product"),
        bodegaOrigen: formData.get("source warehouse"),
        bodegaDestino: formData.get("destination warehouse"),
        user: user.id,
      }),
    })
      .then(() => {
        alert(`${_.upperFirst(transactionsTab)} has been successfully made.`);
        clearFormFields(formRef);
      })
      .catch((error) => {
        console.error("Error saving the " + transactionsTab, error);
        alert(`There was an error processing the ${transactionsTab}.`);
      });
  };

  return (
    <Wrapper>
      <Header>{_.upperFirst(transactionsTab)} a product</Header>
      <FormWrapper>
        <form ref={formRef} onSubmit={handleFormSubmit}>
          <InputRow>
            {formInfo.map(({ title, description, type }, index) => (
              <InputWrapper key={index}>
                <Input title={title} description={description} type={type} />
              </InputWrapper>
            ))}
          </InputRow>
          <ButtonWrapper isNotAPhone={isNotAPhone}>
            <Button type="submit" text="Save" />
          </ButtonWrapper>
        </form>
      </FormWrapper>
    </Wrapper>
  );
};

export default Transactions;
