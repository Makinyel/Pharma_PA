import { React, useRef } from "react";

import Button from "../components/button/Button";
import Input from "../components/input/Input";

import { thirdPartyForminfo as formInfo } from "../utils/form-info/third-party.forminfo.js";

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

const ThirdParty = () => {
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");
  const formRef = useRef(null);

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    await fetch(endpoints.thirdParty.save, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: formData.get("name"),
        documentType: formData.get("document type"),
        document: formData.get("document"),
        personType: formData.get("third party type"),
        location: formData.get("location"),
        phone: formData.get("phone"),
        email: formData.get("email"),
      }),
    })
      .then(() => {
        alert(`Third party has been successfully saved.`);
        clearFormFields(formRef);
      })
      .catch((error) => {
        console.error("Error saving the third party", error);
        alert(`There was an error saving the third party.`);
      });
  };

  return (
    <Wrapper>
      <Header>Add a third party</Header>
      <FormWrapper>
        <form ref={formRef} onSubmit={handleFormSubmit}>
          <InputRow>
            {formInfo.map(({ title, description, type }) => (
              <InputWrapper key={title}>
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

export default ThirdParty;
