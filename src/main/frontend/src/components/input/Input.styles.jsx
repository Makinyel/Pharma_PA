import styled from "styled-components";

export const Container = styled.div`
  padding: 1.5rem 2.5rem;
  max-width: 90%;

  h3 {
    font-weight: bold;
    font-size: 1.125rem;
  }

  p {
    padding: 1rem 0rem;
  }

  select,
  input[type="text"] {
    height: 2.5rem;
    width: 90%;
    border: 1px solid lightblue;
    border-radius: 0.25rem;
    font-size: 1rem;
    padding: 0.75rem;
  }

  select {
    height: 3rem;
  }
`;
